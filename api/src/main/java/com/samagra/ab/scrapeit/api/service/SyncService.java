package com.samagra.ab.scrapeit.api.service;

import org.springframework.amqp.AmqpException;

public interface SyncService {

	void enqueueSyncRequest() throws AmqpException;

}
