package com.fastcampus.projectboardadmin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.search.MeterNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * actuator에 접근하기 위한 비즈니스 서비스
 */
@RequiredArgsConstructor
@Service
public class VisitCounterService {

	private static final List<String> viewEndpoints = List.of(
		"/management/articles",
		"/management/article-comments",
		"/management/user-accounts",
		"/admin/members"
	);
	private final MeterRegistry meterRegistry;

	public long visitCount() {
		long sum;
		try {
			// `/actuator/metrics` 경로에 존재하는 라우트명
			sum = meterRegistry.get("http.server.requests")
				.timers().stream()
				.filter(timer -> viewEndpoints.contains(timer.getId().getTag("uri")))
				.mapToLong(Timer::count)
				.sum();
		} catch (MeterNotFoundException e) {
			sum = 0L;
		}
		return sum;
	}
}
