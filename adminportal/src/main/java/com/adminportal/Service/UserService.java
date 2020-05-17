package com.adminportal.Service;

import java.util.Set;

import com.adminportal.Security.PasswordResetToken;
import com.adminportal.Security.UserRole;
import com.adminportal.models.User;

public interface UserService {

	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	User save(User user);

}
