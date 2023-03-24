package com.fastcampus.projectboardadmin.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fastcampus.projectboardadmin.service.VisitCounterService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class VisitCounterControllerAdvice {

	private final VisitCounterService visitCounterService;

	@ModelAttribute("visitCount")
	public Long visitCount() {
		return visitCounterService.visitCount();
	}
}
