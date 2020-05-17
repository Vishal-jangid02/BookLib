package com.bookstore.Service;

import java.util.Optional;

import com.bookstore.models.UserPayment;

public interface UserPaymentService {

	Optional<UserPayment> findById(Long creditCardId);

	void removeById(Long creditCardId);

}
