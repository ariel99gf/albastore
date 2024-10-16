package com.albastore.service;

import com.albastore.domain.User;
import com.albastore.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UserService {

	@Inject
	UserRepository userRepository;

	public List<User> listAllUsers() {
		return userRepository.listAll();
	}

	public User findUserById(Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	public void createUser(User user) {
		userRepository.persist(user);
	}

	@Transactional
	public void updateUser(Long id, User user) {
		User entity = userRepository.findById(id);
		if (entity != null) {
			entity.name = user.name;
			entity.email = user.email;
			entity.username = user.username;
			entity.password = user.password;
		}
	}

	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public User authenticate(String username, String password) {
		return userRepository.find("username", username)
				.firstResultOptional()
				.filter(user -> user.password.equals(password))
				.orElse(null);
	}
}