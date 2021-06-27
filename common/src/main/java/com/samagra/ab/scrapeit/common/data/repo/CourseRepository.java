package com.samagra.ab.scrapeit.common.data.repo;

import com.samagra.ab.scrapeit.common.data.model.Course;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends PagingAndSortingRepository<Course, String> {

}
