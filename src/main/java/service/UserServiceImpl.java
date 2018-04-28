package service;

import dao.UserRepository;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public void createUser(User user) {
		userRepository.create(user);
	}

	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.listUsers();
	}

	@Transactional(readOnly = true)
	public User findUser(Integer id) {
		return userRepository.getUser(id);
	}

	@Transactional
	public void deleteUser(int id) {
		userRepository.delete(id);
	}

	@Transactional(readOnly = true)
	public boolean findUserByEmail(String email1) {
		return userRepository.findByLogin(email1);
	}

	@Transactional
    public void updateUser(Integer userId, User user) {
		userRepository.update(userId, user);
    }

	@Transactional(readOnly = true)
	public List<User> findUserByName(String value) {
		return userRepository.findByName(value);
	}

}
