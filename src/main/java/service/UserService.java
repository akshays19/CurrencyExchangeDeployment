package service;

import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dao.UserDaoImpl;

/**
 * Service class to invoke UserDao
 * @author Akshay
 *
 */
@Service
public class UserService {
	@Autowired
	private UserDaoImpl userDao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.registerUser(user);
	}

	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}
}
