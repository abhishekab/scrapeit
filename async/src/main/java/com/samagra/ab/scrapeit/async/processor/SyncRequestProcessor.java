package com.samagra.ab.scrapeit.async.processor;

import java.io.IOException;

import com.samagra.ab.scrapeit.async.service.SyncCoursesService;
import com.samagra.ab.scrapeit.common.config.RabbitMqConfiguration;
import com.samagra.ab.scrapeit.common.enums.SyncMessage;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMqConfiguration.SYNC_QUEUE_NAME)
public class SyncRequestProcessor {

	private final SyncCoursesService syncCoursesService;

	public SyncRequestProcessor(SyncCoursesService syncCoursesService) {
		this.syncCoursesService = syncCoursesService;
	}

	@RabbitHandler
	public void process(SyncMessage syncMessage) throws IOException {
		System.out.println("Message received:" + syncMessage.name());
		if (syncMessage == SyncMessage.SYNC) {
			this.syncCoursesService.syncCourses();
		}

	}

}
