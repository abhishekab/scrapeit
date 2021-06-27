package com.samagra.ab.scrapeit.common.data.persistence;

import java.util.List;

import com.samagra.ab.scrapeit.common.data.model.Course;
import com.samagra.ab.scrapeit.common.data.repo.CourseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CoursePersistenceImpl implements CoursePersistence {

	private final CourseRepository courseRepository;

	public CoursePersistenceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public void saveAll(List<Course> courseList) {
		this.courseRepository.saveAll(courseList);
	}

	@Override
	public List<Course> getPaginatedCourses(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		Page<Course> pagedResult = this.courseRepository.findAll(paging);
		return pagedResult.toList();
	}

}
