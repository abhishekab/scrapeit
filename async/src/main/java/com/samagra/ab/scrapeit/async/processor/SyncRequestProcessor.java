package com.samagra.ab.scrapeit.async.processor;

import com.samagra.ab.scrapeit.common.config.RabbitMqConfiguration;

import com.samagra.ab.scrapeit.common.enums.SyncMessage;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMqConfiguration.SCRAPER_QUEUE_NAME)
public class SyncRequestProcessor {

	@RabbitHandler
	public void process(SyncMessage syncMessage) throws InterruptedException {
		System.out.println("Message received:" + syncMessage.name());
		Thread.sleep(10000);
		System.out.println("Synced");
	}

}
