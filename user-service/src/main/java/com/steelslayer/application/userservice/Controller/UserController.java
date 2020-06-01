package com.steelslayer.application.userservice.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.steelslayer.application.userservice.Entity.User;
import com.steelslayer.application.userservice.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/loginUser")
	public User loginUser(@RequestBody Map<String,Object> principal){
		return userService.loginUser((Map<String, Object>) principal.get("attributes"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id){
		return userService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id){
		return userService.deleteById(id);
	}
	
}
