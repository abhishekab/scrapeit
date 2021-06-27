package com.samagra.ab.scrapeit.api.dto;

import java.util.Date;

import com.samagra.ab.scrapeit.common.data.model.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

	String id;

	String title;

	String url;

	String explorerSummary;

	String explorerInstructorName;

	Boolean enrollment;

	Boolean openForRegistration;

	Boolean showInExplorer;

	Date startDate;

	Date endDate;

	Date examDate;

	Date enrollmentEndDate;

	String estimatedWorkload;

	String tags;

	String featured;

	String coursePictureUrl;

	int credits;

	int weeks;

	String nodeCode;

	String instructorInstitute;

	String ncCode;

	public CourseDto(Course course) {
		this.id = course.getId();
		this.title = course.getTitle();
		this.url = course.getUrl();
		this.explorerSummary = course.getExplorerSummary();
		this.explorerInstructorName = course.getExplorerInstructorName();
		this.enrollment = course.getEnrollment();
		this.openForRegistration = course.getOpenForRegistration();
		this.showInExplorer = course.getShowInExplorer();
		this.startDate = course.getStartDate();
		this.endDate = course.getEndDate();
		this.examDate = course.getExamDate();
		this.enrollmentEndDate = course.getEnrollmentEndDate();
		this.estimatedWorkload = course.getEstimatedWorkload();
		this.tags = course.getTags();
		this.featured = course.getFeatured();
		this.coursePictureUrl = course.getCoursePictureUrl();
		this.credits = course.getCredits();
		this.weeks = course.getWeeks();
		this.nodeCode = course.getNodeCode();
		this.instructorInstitute = course.getInstructorInstitute();
		this.ncCode = course.getNcCode();
	}

}
