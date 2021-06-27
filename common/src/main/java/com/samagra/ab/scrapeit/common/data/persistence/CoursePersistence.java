package com.samagra.ab.scrapeit.common.data.persistence;

import java.util.List;

import com.samagra.ab.scrapeit.common.data.model.Course;

public interface CoursePersistence {

	void saveAll(List<Course> courseList);

}
