package com.samagra.ab.scrapeit.api.service;

import java.util.List;

import com.samagra.ab.scrapeit.api.dto.CourseDto;

public interface CourseService {

	List<CourseDto> getPaginatedCourses(int page, int size);

}
