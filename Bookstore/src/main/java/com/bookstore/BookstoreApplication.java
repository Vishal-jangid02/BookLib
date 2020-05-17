package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.Security.Role;
import com.bookstore.Security.UserRole;
import com.bookstore.Service.UserService;
import com.bookstore.Utility.SecurityUtility;
import com.bookstore.models.User;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Override
	public void run(String... arg) throws Exception{
		
		User user1 = new User();
		user1.setFirstName("vishal");
		user1.setLastName("jangid");
		user1.setUsername("V");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("vishaljangid814@gmail.com");
		Set<UserRole> userRoles = new HashSet<UserRole>();
		
		Role role1 = new Role();	
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);
		
	}

}
