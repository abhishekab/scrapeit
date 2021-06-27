package com.samagra.ab.scrapeit.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoursesResponse {

	List<CourseDto> courses;

}
