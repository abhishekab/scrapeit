package com.samagra.ab.scrapeit.async.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samagra.ab.scrapeit.async.dto.swayamapiresponse.Root;

import org.springframework.stereotype.Service;

@Service
public class ScrapeSwayamSyncCoursesServiceImpl implements SyncCoursesService {

	private final HttpService httpService;

	private final Integer DEFAULT_PAGE_LIMIT = 100;

	/**
	 * One parameter expected: endCursor.
	 */
	private final String SWAYAM_GQL_QUERY_PAGINATION_AFTER_TEMPLATE = "after:\"%s\"";

	/**
	 * Two parameters expected: First: Number of courses to be fetched in the request.
	 * integer type Second: If after is applicable, after string -> Example
	 * after:"blablablabla" or blank ""
	 */
	private final String SWAYAM_GQL_QUERY_PAGINATION_STRING_TEMPLATE = "first:%d%s";

	/**
	 * One parameters expected: Pagination field String. Example:
	 * first:100,after:"xyzaasasasaas"
	 */
	private final String SWAYAM_GQL_QUERY_TEMPLATE = "{courseList(args: {includeClosed: false, filterText: \"\", category: \"\", status: \"all\", tags: \"\", duration: \"all\", examDate: \"all\", credits: \"all\", ncCode: \"all\",   }, %s) {edges {node {  id, title, url, explorerSummary,  explorerInstructorName, enrollment {enrolled},   openForRegistration, showInExplorer,  startDate, endDate, examDate, enrollmentEndDate, estimatedWorkload, category {name, category, parentId},  tags {name}, featured, coursePictureUrl, credits, weeks, nodeCode, instructorInstitute, ncCode}}, pageInfo {endCursor, hasNextPage}}}";

	/**
	 * One Parameter expected: UrlEncoded GqlQuery.
	 */
	private final String SWAYAM_GQL_API_TEMPLATE = "https://swayam.gov.in/modules/gql/query?q=%s";

	public ScrapeSwayamSyncCoursesServiceImpl(HttpService httpService) {
		this.httpService = httpService;
	}

	@Override
	public void syncCourses() throws IOException {
		callSwayamGqlApiRecursive(this.DEFAULT_PAGE_LIMIT, null);
		System.out.println("Finished Syncing");
	}

	private void callSwayamGqlApiRecursive(Integer pageLimit, String endCursor) throws IOException {
		String paginationString = String.format(this.SWAYAM_GQL_QUERY_PAGINATION_STRING_TEMPLATE, pageLimit,
				(endCursor != null) ? String.format(this.SWAYAM_GQL_QUERY_PAGINATION_AFTER_TEMPLATE, endCursor) : "");
		String gqlQuery = String.format(this.SWAYAM_GQL_QUERY_TEMPLATE, paginationString);
		String gqlQueryApiUri = String.format(this.SWAYAM_GQL_API_TEMPLATE,
				URLEncoder.encode(gqlQuery, StandardCharsets.UTF_8.toString()));
		String rawResponse = this.httpService.doGetRequest(gqlQueryApiUri);
		// The rawResponse is : )]}' {JSON OBJECT HERE}. So to extract the json string:
		// Substring from the first occurence of {
		// TODO: Maybe use regex here
		String jsonStringFromRawResponse = rawResponse.substring(rawResponse.indexOf('{'));
		System.out.println(jsonStringFromRawResponse);
		System.out.println("____________________________________________________");
		Root root = new ObjectMapper().readValue(jsonStringFromRawResponse, Root.class);
		if (root.getData().getCourseList().getPageInfo().isHasNextPage()) {
			callSwayamGqlApiRecursive(pageLimit, root.getData().getCourseList().getPageInfo().getEndCursor());
		}
	}

}
