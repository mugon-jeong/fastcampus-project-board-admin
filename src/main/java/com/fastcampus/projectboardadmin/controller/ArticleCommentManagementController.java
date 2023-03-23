package com.fastcampus.projectboardadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fastcampus.projectboardadmin.dto.response.ArticleCommentResponse;
import com.fastcampus.projectboardadmin.service.ArticleCommentManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/management/article-comments")
@RequiredArgsConstructor
public class ArticleCommentManagementController {
	private final ArticleCommentManagementService articleCommentManagementService;

	@GetMapping
	public String articleComments(Model model) {
		model.addAttribute(
			"comments",
			articleCommentManagementService.getArticleComments().stream().map(ArticleCommentResponse::of).toList()
		);
		return "management/article-comments";
	}

	@ResponseBody
	@GetMapping("/{articleCommentId}")
	public ArticleCommentResponse articleComment(@PathVariable Long articleCommentId) {
		return ArticleCommentResponse.of(articleCommentManagementService.getArticleComment(articleCommentId));
	}

	@PostMapping("/{articleCommentId}")
	public String deleteArticleComment(@PathVariable Long articleCommentId) {
		articleCommentManagementService.deleteArticleComment(articleCommentId);

		return "redirect:/management/article-comments";
	}
}
