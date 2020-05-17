package com.bookstore.ServiceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.Repository.UserPaymentRepository;
import com.bookstore.Service.UserPaymentService;
import com.bookstore.models.UserPayment;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{

	@Autowired
	UserPaymentRepository userPaymentRepository; 
	
	@Override
	public Optional<UserPayment> findById(Long creditCardId) {
		
		return userPaymentRepository.findById(creditCardId);
	}

	@Override
	public void removeById(Long creditCardId) {
		
		userPaymentRepository.deleteById(creditCardId);
	}

}
