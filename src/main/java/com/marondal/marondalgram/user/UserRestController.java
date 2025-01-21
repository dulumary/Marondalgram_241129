package com.marondal.marondalgram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.marondalgram.common.dto.Response;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	private UserService userService;
	
	public UserRestController(UserService userService) {	
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Response<Object> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		String result = null;
		if(userService.addUser(loginId, password, name, email)) {
			result = "success";
		} else {
			result = "fail";
		}
		Response<Object> response = new Response<>(result, null);
		return response;
	}
	
	@Operation(summary="아이디 중복확인", description="아이디를 전달 받고 중복여부를 판단한다.")
	@GetMapping("/duplicate-id")
	public Response<Map<String, Boolean>> isDuplicateId(
			@Parameter(description="중복확인할 대상 ID")
			@RequestParam("loginId") String loginId) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		resultMap.put("isDuplicate", userService.isDuplicateId(loginId));
		Response<Map<String, Boolean>> response = new Response<>("success", resultMap);
		
		return response;
		
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			,  HttpSession session) {
		
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}








