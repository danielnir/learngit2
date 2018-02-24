package com.nirumand.messaging.rabbitmq.Service;

import com.nirumand.messaging.rabbitmq.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("consumer")
@Service
public class EventConsumer {

	private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

	private final RabbitTemplate rabbitTemplate;

	@Autowired
	public EventConsumer(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@RabbitListener(queues = "${event.queues.system}")
	public void receiveMessage(final Event event) {
		logger.info("Received message as specific class: {}", event.toString());
	}
}
