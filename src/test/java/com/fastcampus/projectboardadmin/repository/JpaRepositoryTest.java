package com.fastcampus.projectboardadmin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fastcampus.projectboardadmin.domain.AdminAccount;
import com.fastcampus.projectboardadmin.domain.constant.RoleType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

	private final AdminAccountRepository adminAccountRepository;

	JpaRepositoryTest(@Autowired AdminAccountRepository adminAccountRepository) {
		this.adminAccountRepository = adminAccountRepository;
	}

	@DisplayName("회원 정보 select 테스트")
	@Test
	void givenUserAccount_whenSelecting_thenWorksFine() {
		// Given

		// When
		List<AdminAccount> userAccounts = adminAccountRepository.findAll();
		// Then
		assertThat(userAccounts)
			.isNotNull()
			.hasSize(4);

	}

	@DisplayName("회원 정보 insert 테스트")
	@Test
	void givenUserAccount_WhenInserting_thenWorksFine() {
		// given
		Long previousCount = adminAccountRepository.count();
		AdminAccount userAccount = AdminAccount.of("test", "pw", Set.of(RoleType.DEVELOPER), null,
			null, null);

		// when
		adminAccountRepository.save(userAccount);
		// then
		assertThat(adminAccountRepository.count()).isEqualTo(previousCount + 1);
	}

	@DisplayName("회원 정보 update 테스트")
	@Test
	void givenUserAccountAndRoleType_WhenUpdating_thenWorksFine() {
		// given
		AdminAccount userAccount = adminAccountRepository.getReferenceById("uno");
		userAccount.addRoleType(RoleType.DEVELOPER);
		userAccount.addRoleTypes(List.of(RoleType.USER, RoleType.USER));
		userAccount.removeRoleType(RoleType.ADMIN);

		// when
		AdminAccount updatedAccount = adminAccountRepository.saveAndFlush(userAccount);
		// then
		assertThat(updatedAccount)
			.hasFieldOrPropertyWithValue("userId", "uno")
			.hasFieldOrPropertyWithValue("roleTypes", Set.of(RoleType.DEVELOPER, RoleType.USER));
	}

	@DisplayName("회원 정보 delete 테스트")
	@Test
	void givenUserAccount_WhenDeleting_thenWorksFine() {
		// given
		Long previousCount = adminAccountRepository.count();
		AdminAccount userAccount = adminAccountRepository.getReferenceById("uno");

		// when
		adminAccountRepository.delete(userAccount);
		// then
		assertThat(adminAccountRepository.count()).isEqualTo(previousCount - 1);
	}

	@EnableJpaAuditing
	@TestConfiguration
	static class TestJpaConfig {

		@Bean
		AuditorAware<String> auditorAware() {
			return () -> Optional.of("uno");
		}
	}
}
