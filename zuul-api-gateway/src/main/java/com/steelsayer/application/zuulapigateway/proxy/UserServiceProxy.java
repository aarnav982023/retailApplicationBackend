package com.steelsayer.application.zuulapigateway.proxy;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("users")
public interface UserServiceProxy {
	@PostMapping("/loginUser")
	public Map<String,String> loginUser(Object principal);
}
