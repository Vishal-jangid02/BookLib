package com.adminportal.ServiceImp;

import java.util.Set;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.Repository.RoleRepository;
import com.adminportal.Repository.UserRepository;
import com.adminportal.Security.UserRole;
import com.adminportal.Service.UserService;
import com.adminportal.models.User;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository; 
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
	
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			//throw new Exception("user already exists.nothing will be done");
			LOG.info("user already exists.nothing will be done"+user.getUsername());
		}
		else {
			
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			
			localUser = userRepository.save(user);
		}
		return localUser;
	}

	@Override
	public User save(User user) {
		
		return userRepository.save(user);
	}

	
	
	
}
