package com.fastcampus.projectboardadmin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.fastcampus.projectboardadmin.config.TestSecurityConfig;

@DisplayName("View 루트 컨트롤러")
@Import(TestSecurityConfig.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

	private final MockMvc mvc;

	public MainControllerTest(@Autowired MockMvc mvc) {
		this.mvc = mvc;
	}

	@DisplayName("[view][GET] 루트 페이지 -> 게시글 관리 페이지 Forwarding")
	@Test
	void givenNothing_whenRequestingRootView_thenForwardsToArticleManagementView()
		throws Exception {
		// Given

		// When & Then
		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("forward:/management/articles"))
			.andExpect(forwardedUrl("/management/articles"));
	}
}
