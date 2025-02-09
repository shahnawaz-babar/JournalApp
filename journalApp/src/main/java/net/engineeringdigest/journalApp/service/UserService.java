package net.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;

//controller ----> service ---> repositry 
@Component
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void saveEntry(User user) {
		try {
			userRepository.save(user);
			
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(ObjectId id) {
		return userRepository.findById(id);

	}

	public void deleteById(ObjectId id) {
		userRepository.deleteById(id);
	}

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
