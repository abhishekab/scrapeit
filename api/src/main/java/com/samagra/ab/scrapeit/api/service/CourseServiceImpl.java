package com.samagra.ab.scrapeit.api.service;

import java.util.List;
import java.util.stream.Collectors;

import com.samagra.ab.scrapeit.api.dto.CourseDto;
import com.samagra.ab.scrapeit.common.data.persistence.CoursePersistence;

import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	private final CoursePersistence coursePersistence;

	public CourseServiceImpl(CoursePersistence coursePersistence) {
		this.coursePersistence = coursePersistence;
	}

	@Override
	public List<CourseDto> getPaginatedCourses(int page, int size) {
		return this.coursePersistence.getPaginatedCourses(page, size).stream().map((it) -> new CourseDto(it))
				.collect(Collectors.toList());
	}

}
