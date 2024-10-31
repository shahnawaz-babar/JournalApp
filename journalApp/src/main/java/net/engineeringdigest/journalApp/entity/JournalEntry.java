package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.crypto.Data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "journal_entries")
@lombok.Data
@NoArgsConstructor
public class JournalEntry {

	@Id
	private ObjectId id;
	@NonNull
	private String title;
	@NonNull
	private String content;
	private LocalDateTime date;

	

	
	
}
