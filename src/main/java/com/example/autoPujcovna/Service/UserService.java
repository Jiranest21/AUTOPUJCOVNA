package com.example.autoPujcovna.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public void addUser(User user) {
		Optional<User> userOptional = userRepository.findStudentbyEmail(user.getEmail());
		if (userOptional.isPresent()) {
			throw new IllegalStateException("E-mail taken");
		}
		userRepository.save(user);
	}

	public void deleteUser(Long userId) {
		boolean exists = userRepository.existsById(userId);
		if (!exists) {
			throw new IllegalStateException("user with id " + userId + " does not exist");
		}
		userRepository.deleteById(userId);
	}

	@Transactional
	public void updateUser(Long userId, String username, String email) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalStateException("student with id " + userId + ""));

		if (username != null &&
				username.length() > 0 &&
				!Objects.equals(user.getUsername(), username)) {
			user.setUsername(username);
		}

		if (email != null &&
				email.length() > 0 &&
				!Objects.equals(user.getEmail(), email)) {
			Optional<User> studentOptional = userRepository.findStudentbyEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("E-mail is taken");
			}
			user.setEmail(email);
		}
	}

	public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

	


}
