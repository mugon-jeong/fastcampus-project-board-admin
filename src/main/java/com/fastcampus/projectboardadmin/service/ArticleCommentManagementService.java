package com.fastcampus.projectboardadmin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fastcampus.projectboardadmin.dto.ArticleCommentDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ArticleCommentManagementService {
	public List<ArticleCommentDto> getArticleComments() {
		return List.of();
	}

	public ArticleCommentDto getArticleComment(Long articleCommentId) {
		return null;
	}

	public void deleteArticleComment(Long articleCommentId) {
	}
}
