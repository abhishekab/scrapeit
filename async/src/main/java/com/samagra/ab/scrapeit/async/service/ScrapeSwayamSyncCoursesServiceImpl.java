package com.samagra.ab.scrapeit.async.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samagra.ab.scrapeit.async.dto.swayamapiresponse.Node;
import com.samagra.ab.scrapeit.async.dto.swayamapiresponse.Root;
import com.samagra.ab.scrapeit.common.data.model.Course;
import com.samagra.ab.scrapeit.common.data.persistence.CoursePersistence;

import org.springframework.stereotype.Service;

@Service
public class ScrapeSwayamSyncCoursesServiceImpl implements SyncCoursesService {

	private final HttpService httpService;

	private final CoursePersistence coursePersistence;

	private static final Integer DEFAULT_PAGE_LIMIT = 100;

	/**
	 * One parameter expected: endCursor.
	 */
	private static final String SWAYAM_GQL_QUERY_PAGINATION_AFTER_TEMPLATE = "after:\"%s\"";

	/**
	 * Two parameters expected: First: Number of courses to be fetched in the request.
	 * integer type Second: If after is applicable, after string -> Example
	 * after:"blablablabla" or blank ""
	 */
	private static final String SWAYAM_GQL_QUERY_PAGINATION_STRING_TEMPLATE = "first:%d%s";

	/**
	 * One parameters expected: Pagination field String. Example:
	 * first:100,after:"xyzaasasasaas"
	 */
	private static final String SWAYAM_GQL_QUERY_TEMPLATE = "{courseList(args: {includeClosed: false, filterText: \"\", category: \"\", status: \"all\", tags: \"\", duration: \"all\", examDate: \"all\", credits: \"all\", ncCode: \"all\",   }, %s) {edges {node {  id, title, url, explorerSummary,  explorerInstructorName, enrollment {enrolled},   openForRegistration, showInExplorer,  startDate, endDate, examDate, enrollmentEndDate, estimatedWorkload, category {name, category, parentId},  tags {name}, featured, coursePictureUrl, credits, weeks, nodeCode, instructorInstitute, ncCode}}, pageInfo {endCursor, hasNextPage}}}";

	/**
	 * One Parameter expected: UrlEncoded GqlQuery.
	 */
	private static final String SWAYAM_GQL_API_TEMPLATE = "https://swayam.gov.in/modules/gql/query?q=%s";

	public ScrapeSwayamSyncCoursesServiceImpl(HttpService httpService, CoursePersistence coursePersistence) {
		this.httpService = httpService;
		this.coursePersistence = coursePersistence;
	}

	@Override
	public void syncCourses() throws IOException {
		callSwayamGqlApiRecursive(DEFAULT_PAGE_LIMIT, null);
		System.out.println("Finished Syncing");
	}

	private void callSwayamGqlApiRecursive(Integer pageLimit, String endCursor) throws IOException {
		String paginationString = String.format(SWAYAM_GQL_QUERY_PAGINATION_STRING_TEMPLATE, pageLimit,
				(endCursor != null) ? String.format(SWAYAM_GQL_QUERY_PAGINATION_AFTER_TEMPLATE, endCursor) : "");
		String gqlQuery = String.format(SWAYAM_GQL_QUERY_TEMPLATE, paginationString);
		String gqlQueryApiUri = String.format(SWAYAM_GQL_API_TEMPLATE,
				URLEncoder.encode(gqlQuery, StandardCharsets.UTF_8.toString()));
		String rawResponse = this.httpService.doGetRequest(gqlQueryApiUri);
		// The rawResponse is : )]}' {JSON OBJECT HERE}. So to extract the json string:
		// Substring from the first occurence of {

		// TODO: Maybe use regex here
		String jsonStringFromRawResponse = rawResponse.substring(rawResponse.indexOf('{'));
		// System.out.println(jsonStringFromRawResponse);
		// System.out.println("____________________________________________________");
		Root root = new ObjectMapper().readValue(jsonStringFromRawResponse, Root.class);

		List<Course> courseList = root.getData().getCourseList().getEdges().stream()
				.map((it) -> getCourseFromNode(it.getNode())).collect(Collectors.toList());
		this.coursePersistence.saveAll(courseList);
		if (root.getData().getCourseList().getPageInfo().isHasNextPage()) {
			callSwayamGqlApiRecursive(pageLimit, root.getData().getCourseList().getPageInfo().getEndCursor());
		}
	}

	private Course getCourseFromNode(Node node) {
		Course course = new Course();
		course.setId(node.getId());
		course.setTitle(node.getTitle());
		course.setUrl(node.getUrl());
		course.setExplorerSummary(node.getExplorerSummary());
		course.setExplorerInstructorName(node.getExplorerInstructorName());
		course.setEnrollment((node.getEnrollment() == null) ? null : node.getEnrollment().isEnrolled());
		course.setOpenForRegistration(node.isOpenForRegistration());
		course.setShowInExplorer(node.isShowInExplorer());
		course.setStartDate(node.getStartDate());
		course.setEndDate(node.getEndDate());
		course.setExamDate(node.getExamDate());
		course.setEnrollmentEndDate(node.getEnrollmentEndDate());
		course.setEstimatedWorkload(node.getEstimatedWorkload());
		course.setTags(node.getTags());
		course.setFeatured(node.getFeatured());
		course.setCoursePictureUrl(node.getCoursePictureUrl());
		course.setCredits(node.getCredits());
		course.setWeeks(node.getWeeks());
		course.setNodeCode(node.getNodeCode());
		course.setInstructorInstitute(node.getInstructorInstitute());
		course.setNcCode(node.getNcCode());
		return course;
	}

}
