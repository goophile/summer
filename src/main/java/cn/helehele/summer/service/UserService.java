package cn.helehele.summer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.helehele.summer.bean.User;
import cn.helehele.summer.mapper.UserMapper;

@Component
@Transactional
public class UserService {

	@Autowired
	UserMapper userMapper;

	public List<User> getUsers(int pageIndex) {
		int pageSize = 20;
		return userMapper.getAll((pageIndex - 1) * pageSize, pageSize);
	}

	public User getUserById(long id) {
		User user = userMapper.getById(id);
		if (user == null) {
			throw new RuntimeException("User not found by id.");
		}
		return user;
	}

	public User login(String email, String password) {
		User user = userMapper.getByEmail(email);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		throw new RuntimeException("User not found or wrong password.");
	}

	public User register(String email, String password, String name) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);
		user.setUpdatedAt(System.currentTimeMillis());
		userMapper.insert(user);
		return user;
	}

	public User updateUser(Long id, String name, String password) {
		User user = getUserById(id);
		user.setName(name);
		user.setPassword(password);
		user.setUpdatedAt(System.currentTimeMillis());
		userMapper.update(user);
		return user;
	}

	public void deleteUser(Long id) {
		userMapper.deleteById(id);
	}
}
