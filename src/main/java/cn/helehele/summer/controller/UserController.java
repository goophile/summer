package cn.helehele.summer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.helehele.summer.bean.User;
import cn.helehele.summer.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> users(@RequestParam(required = false) Integer page) {
		if (page == null) {
			page = 1;
		}
		return userService.getUsers(page);
	}

	@GetMapping("/users/{id}")
	public User user(@PathVariable("id") long id) {
		return userService.getUserById(id);
	}

	@PatchMapping("/users/{id}")
	public User updateUser(@PathVariable("id") long id, @RequestBody UpdateRequest updateRequest) {
		return userService.updateUser(id, updateRequest.name, updateRequest.password);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		userService.deleteUser(id);
	}

	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody RegisterRequest registerRequest) {
		try {
			User user = userService.register(registerRequest.email, registerRequest.password, registerRequest.name);
			return Map.of("user", user);
		} catch (Exception e) {
			return Map.of("error", "REGISTER_FAILED", "message", e.getMessage());
		}
	}

	@PostMapping("/signin")
	public Map<String, Object> signin(@RequestBody SignInRequest signinRequest) {
		try {
			User user = userService.login(signinRequest.email, signinRequest.password);
			return Map.of("user", user);
		} catch (Exception e) {
			return Map.of("error", "SIGNIN_FAILED", "message", e.getMessage());
		}
	}

	public static class RegisterRequest {
		public String email;
		public String name;
		public String password;
	}

	public static class SignInRequest {
		public String email;
		public String password;
	}

	public static class UpdateRequest {
		public String name;
		public String password;
	}
}
