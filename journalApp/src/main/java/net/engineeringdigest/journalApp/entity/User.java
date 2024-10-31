package net.engineeringdigest.journalApp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Trunc;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "users")
@lombok.Data
public class User {

	@Id
	private ObjectId id;
	
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	@DBRef
	private List<JournalEntry> journalEntries=new ArrayList<JournalEntry>();

	
	private List<String> roles;
}