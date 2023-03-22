package com.fastcampus.projectboardadmin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fastcampus.projectboardadmin.dto.UserAccountDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserAccountManagementService {
	public List<UserAccountDto> getUserAccounts() {
		return List.of();
	}

	public UserAccountDto getUserAccount(String userId) {
		return null;
	}

	public void deleteUserAccount(String userId) {

	}
}
