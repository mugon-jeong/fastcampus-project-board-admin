package com.fastcampus.projectboardadmin.config;

import static org.mockito.BDDMockito.*;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.fastcampus.projectboardadmin.service.VisitCounterService;

@TestConfiguration
public class GlobalControllerConfig {

	@MockBean
	private VisitCounterService visitCounterService;

	@BeforeTestMethod
	public void securitySetup() {
		given(visitCounterService.visitCount()).willReturn(0L);
	}

}
