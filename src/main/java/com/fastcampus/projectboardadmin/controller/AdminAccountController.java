package com.fastcampus.projectboardadmin.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fastcampus.projectboardadmin.dto.response.AdminAccountResponse;
import com.fastcampus.projectboardadmin.service.AdminAccountService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminAccountController {

	private final AdminAccountService adminAccountService;

	@GetMapping("/admin/members")
	public String members() {
		return "admin/members";
	}

	@ResponseBody
	@GetMapping("/api/admin/members")
	public List<AdminAccountResponse> getMembers() {
		return adminAccountService.users().stream()
			.map(AdminAccountResponse::from)
			.toList();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	@DeleteMapping("/api/admin/members/{userId}")
	public void delete(@PathVariable String userId) {
		adminAccountService.deleteUser(userId);
	}
}
