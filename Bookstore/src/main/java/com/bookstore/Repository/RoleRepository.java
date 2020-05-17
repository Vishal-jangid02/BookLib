package com.bookstore.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.Security.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
 
	Role findByname(String name);
	
}
