package com.fastcampus.projectboardadmin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fastcampus.projectboardadmin.dto.ArticleDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArticleManagementService {
	public List<ArticleDto> getArticles() {
		return List.of();
	}

	public ArticleDto getArticle(Long articleId) {
		return null;
	}

	public void deleteArticle(Long articleId) {
	}
}

