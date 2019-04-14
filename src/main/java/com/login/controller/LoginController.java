package com.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.login.model.user.GetUsersResponse;
import com.login.model.user.LoginRequest;
import com.login.model.user.RegisterLoginResponse;
import com.login.model.user.RegisterRequest;
import com.login.model.user.VarifyResponse;
import com.login.service.UserService;

@RestController
@RequestMapping("/server")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public RegisterLoginResponse registerUser(@RequestBody final RegisterRequest request) {
		logger.info("RegisterUser controller request [" + request + "]");
		RegisterLoginResponse response = this.userService.saveUser(request);
		logger.info("RegisterUser controller response: " + response);
		return response;
	}

	@PostMapping("/login")
	public RegisterLoginResponse userLogin(@RequestBody LoginRequest request) {
		logger.info("userLogin controller request [" + request + "]");

		logger.info("userLogin controller request [" + request + "]");
		RegisterLoginResponse response = this.userService.findUser(request);
		logger.info("userlogin controller response: " + response);
		return response;
	}

	@GetMapping("/users")
	public GetUsersResponse getUsers(@RequestHeader("X-Auth-Token") String token, @RequestParam("page") int page, @RequestParam("size") int size) {
		logger.info("getuser controller page [" + page + "], size [" + size + "]");
		GetUsersResponse response = this.userService.getUsers(page, size);
		logger.info("getUsers controller response: " + response);
		return response;
	}

	@GetMapping("/verify")
	public VarifyResponse varityUser(@RequestHeader("X-Auth-Token") String token) {
		logger.info("varityUser controller token [" + token + "]");
		VarifyResponse response = this.userService.verify(token);
		logger.info("varityUser controller response: " + response);
		return response;
	}
}
