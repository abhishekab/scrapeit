package com.samagra.ab.scrapeit.api.service;

import com.samagra.ab.scrapeit.common.enums.SyncMessage;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SyncServiceImpl implements SyncService {

	private final RabbitTemplate amqpTemplate;

	private final Queue rabbitQueue;

	public SyncServiceImpl(RabbitTemplate amqpTemplate, Queue rabbitQueue) {
		this.amqpTemplate = amqpTemplate;
		this.rabbitQueue = rabbitQueue;
	}

	public void enqueueSyncRequest() throws AmqpException {
		this.amqpTemplate.convertAndSend(this.rabbitQueue.getName(), SyncMessage.SYNC);
	}

}
