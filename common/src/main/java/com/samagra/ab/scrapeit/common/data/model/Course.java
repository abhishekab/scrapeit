package com.samagra.ab.scrapeit.common.data.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("courses")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Course {

	@Id
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

	// TODO: Save category to DB later
	// List<Category> category;

	String tags;

	String featured;

	String coursePictureUrl;

	int credits;

	int weeks;

	String nodeCode;

	String instructorInstitute;

	String ncCode;

}
