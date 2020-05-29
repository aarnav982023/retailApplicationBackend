package com.steelsayer.application.zuulapigateway.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("users")
public interface UserServiceProxy {
	@PostMapping("/checkSession")
	public Object checkSession(Object principal);
}
