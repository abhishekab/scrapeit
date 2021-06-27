package com.samagra.ab.scrapeit.common.data.persistence;

import java.util.List;

import com.samagra.ab.scrapeit.common.data.model.Course;
import com.samagra.ab.scrapeit.common.data.repo.CourseRepository;

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

}
