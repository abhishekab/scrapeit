package com.samagra.ab.scrapeit.api.controller;

import com.samagra.ab.scrapeit.api.dto.ResponseData;
import com.samagra.ab.scrapeit.api.dto.SyncResponse;
import com.samagra.ab.scrapeit.api.dto.enums.SyncStatus;
import com.samagra.ab.scrapeit.api.exception.DefaultExceptionHandler;
import com.samagra.ab.scrapeit.api.service.CoursesService;
import com.samagra.ab.scrapeit.api.service.SyncService;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/course")
public class CoursesController {

	private final CoursesService coursesService;

	private final SyncService syncService;

	@Autowired
	public CoursesController(CoursesService coursesService, SyncService syncService) {
		this.coursesService = coursesService;
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

}
