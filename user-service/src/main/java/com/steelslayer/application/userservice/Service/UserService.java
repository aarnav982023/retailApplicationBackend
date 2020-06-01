package com.steelslayer.application.userservice.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.steelslayer.application.userservice.Entity.SocialLoginType;
import com.steelslayer.application.userservice.Entity.User;
import com.steelslayer.application.userservice.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<User> findById(long id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user.get());
	}
	
	public ResponseEntity<List<User>> findAll(){
		List<User> users = userRepository.findAll();
		return ResponseEntity.ok(users);
	}
	
	public ResponseEntity<User> create(User user){
		if(user.getId()==null) {
			User newUser = userRepository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	public ResponseEntity<User> deleteById(long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) return ResponseEntity.notFound().build();
		userRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	public User loginUser(Map<String,Object> user) {
		User socialUser = getSocialLoginUser(user);
		Optional<User> newUser = userRepository.findBySocialIdAndSocialLoginType(socialUser.getSocialId(), socialUser.getSocialLoginType());
		if(newUser.isPresent()) {
			return newUser.get();
		}
		else {
			User savedUser = userRepository.save(socialUser);
			return savedUser;
		}
	}
	
	public User getSocialLoginUser(Map<String,Object> socialUser) {
		User user = new User(null,null,null,null,null);
		if(socialUser.get("iss")!=null) {
			user.setEmail(Objects.toString(socialUser.get("email").toString(),null));
			user.setAvatarUrl(Objects.toString(socialUser.get("picture").toString(),null));
			user.setName(Objects.toString(socialUser.get("name").toString(),null));
			user.setSocialId(Objects.toString(socialUser.get("sub").toString(),null));
			user.setSocialLoginType(SocialLoginType.GOOGLE);
			return user;
		}
		else {
			user.setEmail(Objects.toString(socialUser.get("email"),null));
			user.setAvatarUrl(Objects.toString(socialUser.get("avatar_url"),null));
			user.setName(Objects.toString(socialUser.get("name").toString(),null));
			user.setSocialId(Objects.toString(socialUser.get("id").toString(),null));
			user.setSocialLoginType(SocialLoginType.GITHUB);
			return user;
		}
	}
}
