package com.bookstore.ServiceImp;

import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Repository.PasswordResetTokenRepository;
import com.bookstore.Repository.RoleRepository;
import com.bookstore.Repository.UserPaymentRepository;
import com.bookstore.Repository.UserRepository;
import com.bookstore.Security.PasswordResetToken;
import com.bookstore.Security.UserRole;
import com.bookstore.Service.UserService;
import com.bookstore.models.User;
import com.bookstore.models.UserBilling;
import com.bookstore.models.UserPayment;
import com.bookstore.models.UserShipping;

@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository; 

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;  
	
	@Override
	public PasswordResetToken getPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user,final String token) {
		// TODO Auto-generated method stub
		
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
				passwordResetTokenRepository.save(myToken);
		
	}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

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

	@Override
	public void updateUserBilling(UserPayment userPayment, UserBilling userBilling, User user) {
		
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
		
	}

	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		
		List<UserPayment> userPaymentList  = (List<UserPayment>) userPaymentRepository.findAll();
	
		for(UserPayment userPayment : userPaymentList) {
			
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment); 
			}
			else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
				
		}
	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user); 
	}

}
