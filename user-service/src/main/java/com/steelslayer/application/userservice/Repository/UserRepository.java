package com.steelslayer.application.userservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.steelslayer.application.userservice.Entity.SocialLoginType;
import com.steelslayer.application.userservice.Entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findBySocialIdAndSocialLoginType(String socialId,SocialLoginType socialLoginType);
}
