package com.samagra.ab.scrapeit.api.controller;

import com.samagra.ab.scrapeit.api.dto.CoursesResponse;
import com.samagra.ab.scrapeit.api.dto.ResponseData;
import com.samagra.ab.scrapeit.api.dto.SyncResponse;
import com.samagra.ab.scrapeit.api.dto.enums.SyncStatus;
import com.samagra.ab.scrapeit.api.exception.DefaultExceptionHandler;
import com.samagra.ab.scrapeit.api.service.CourseService;
import com.samagra.ab.scrapeit.api.service.SyncService;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/course")
public class CourseController {

	private final CourseService courseService;

	private final SyncService syncService;

	@Autowired
	public CourseController(CourseService courseService, SyncService syncService) {
		this.courseService = courseService;
		this.syncService = syncService;
	}

	@GetMapping("sync")
	public ResponseEntity<ResponseData<SyncResponse>> sync() {
		try {
			this.syncService.enqueueSyncRequest();
		}
		catch (AmqpConnectException amqpConnectException) {
			return new ResponseEntity<>(
					new ResponseData<>(false, new SyncResponse(SyncStatus.FAILED), "Message Broker Unavailable"),
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		catch (AmqpException amqpException) {
			return new ResponseEntity<>(new ResponseData<>(false, new SyncResponse(SyncStatus.FAILED),
					DefaultExceptionHandler.DEFAULT_ERROR_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new ResponseData<>(true, new SyncResponse(SyncStatus.QUEUED), null),
				HttpStatus.ACCEPTED);
	}

	// TODO: Add selection, projection and sorting parameters and follow JSON.api
	// standards
	@GetMapping
	public ResponseEntity<ResponseData<CoursesResponse>> getPaginatedCourses(@RequestParam("page") int page,
			@RequestParam("size") int size) {
		return new ResponseEntity<>(
				new ResponseData<>(true, new CoursesResponse(this.courseService.getPaginatedCourses(page, size)), null),
				HttpStatus.OK);
	}

}
