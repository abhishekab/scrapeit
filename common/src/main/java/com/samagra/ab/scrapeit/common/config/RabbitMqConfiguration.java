package com.samagra.ab.scrapeit.common.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

	/**
	 * Name of the single Queue which takes Syn Requests.
	 */
	public static final String SYNC_QUEUE_NAME = "com.samagra.ab.scraper.queue";

	@Bean
	public Queue queue() {
		return new Queue(SYNC_QUEUE_NAME);
	}

	@Bean
	public MessageConverter simpleMessageConverter() {
		return new SimpleMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {

		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(simpleMessageConverter());
		return rabbitTemplate;
	}

}
