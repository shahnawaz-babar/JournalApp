	package net.engineeringdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.print.attribute.standard.MediaSize.Other;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

	@Autowired
	private JournalEntryService journalEntryService;

	// private Map<Long, JournalEntry> journalEntries=new HashMap<Long,
	// JournalEntry>();
	// controller ----> service ---> repositry
	@Autowired
	private UserService userService;

	
	//read all data in particular user
	@GetMapping("{userName}")
	public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
		User user = userService.findByUserName(userName);
		List<JournalEntry> all = user.getJournalEntries();
		if (all != null && !all.isEmpty()) {
			return new ResponseEntity<>(all, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	//inserrt data in particular user
	@PostMapping("{userName}")
	public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {

		try {
			User user = userService.findByUserName(userName);
 
			myEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(myEntry,userName);
			return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	// read journal entries by id
	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
		if (journalEntry.isPresent()) {
			return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//delete journal entries of user 
	@DeleteMapping("id/{userName}/{myId}")
	public ResponseEntity<?> removeJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {
		journalEntryService.deleteById(myId,userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	//update journal entries of user
	@PutMapping("id/{userName}/{myid}")
	public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myid, @RequestBody JournalEntry newEntry,@PathVariable String userName) {
		JournalEntry old = journalEntryService.findById(myid).orElse(null);
		if (old != null) {
			old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle()
					: old.getTitle());
			old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent()
					: old.getContent());
			journalEntryService.saveEntry(old);
			return new ResponseEntity<>(old, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
