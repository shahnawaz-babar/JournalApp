package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepository.findByUserName(username);
		if(user!=null)
		{
			return org.springframework.security.core.userdetails.User.builder()
					.username(user.getUserName())
					.password(user.getPassword())
					.roles(user.getRoles().toArray(new String[0]))
					.build();

		}
		throw new UsernameNotFoundException("User Not found with username: "+username);
	}

}
