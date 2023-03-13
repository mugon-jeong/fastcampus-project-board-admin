package com.fastcampus.projectboardadmin.controller;

import static org.springframework.data.domain.Sort.Direction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/article-comments")
public class ArticleCommentManagementController {

    @GetMapping
    public String articleComments(
        @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
        Model model
    ) {
        return "management/article-comments";
    }
}
