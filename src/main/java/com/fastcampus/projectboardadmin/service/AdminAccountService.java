package com.fastcampus.projectboardadmin.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fastcampus.projectboardadmin.domain.constant.RoleType;
import com.fastcampus.projectboardadmin.dto.AdminAccountDto;
import com.fastcampus.projectboardadmin.repository.AdminAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAccountService {
	private final AdminAccountRepository adminAccountRepository;

	public Optional<AdminAccountDto> searchUser(String username) {
		return Optional.empty();
	}

	public AdminAccountDto saveUser(String username, String password, Set<RoleType> roleTypes, String email,
		String nickname, String memo) {
		return null;
	}

	public List<AdminAccountDto> users() {
		return List.of();
	}

	public void deleteUser(String username) {

	}
}
