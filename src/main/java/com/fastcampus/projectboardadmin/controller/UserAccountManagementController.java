package com.fastcampus.projectboardadmin.controller;

import static org.springframework.data.domain.Sort.Direction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.projectboardadmin.dto.response.UserAccountResponse;
import com.fastcampus.projectboardadmin.service.UserAccountManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/management/user-accounts")
@RequiredArgsConstructor
public class UserAccountManagementController {
	private final UserAccountManagementService userAccountManagementService;

	@GetMapping
	public String userAccounts(Model model) {
		model.addAttribute(
			"userAccounts",
			userAccountManagementService.getUserAccounts().stream().map(UserAccountResponse::from).toList()
		);
		return "management/user-accounts";
	}

	@ResponseBody
	@GetMapping("/{userId}")
	public UserAccountResponse userAccount(@PathVariable String userId) {
		return UserAccountResponse.from(userAccountManagementService.getUserAccount(userId));
	}

	@PostMapping("/{userId}")
	public String deleteUserAccount(@PathVariable String userId) {
		userAccountManagementService.deleteUserAccount(userId);

		return "redirect:/management/user-accounts";
	}
}
