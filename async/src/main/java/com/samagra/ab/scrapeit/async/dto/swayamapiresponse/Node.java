package com.samagra.ab.scrapeit.async.dto.swayamapiresponse;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

	String id;

	String title;

	String url;

	String explorerSummary;

	String explorerInstructorName;

	Enrollment enrollment;

	boolean openForRegistration;

	boolean showInExplorer;

	Date startDate;

	Date endDate;

	Date examDate;

	Date enrollmentEndDate;

	String estimatedWorkload;

	List<Category> category;

	String tags;

	String featured;

	String coursePictureUrl;

	int credits;

	int weeks;

	String nodeCode;

	String instructorInstitute;

	String ncCode;

}
