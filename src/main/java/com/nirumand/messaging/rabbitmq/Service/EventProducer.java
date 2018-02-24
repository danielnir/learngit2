package com.nirumand.messaging.rabbitmq.Service;

import com.nirumand.messaging.rabbitmq.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("producer")
@Service
public class EventProducer {

	@Value("${event.exchange.name}")
	private String defaultExchange;

	@Value("${event.exchange.key}")
	private String defaultKey;

	private static final Logger log = LoggerFactory.getLogger(EventProducer.class);

	private final RabbitTemplate rabbitTemplate;


	@Autowired
	public EventProducer(final RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(Event event) {
		log.info("Sending message...");
		rabbitTemplate.convertAndSend(defaultExchange, defaultKey, event);
	}

	public void sendMessage(Event event,String exchange, String key) {
		log.debug("Sending message...");
		rabbitTemplate.convertAndSend(exchange, key, event);
	}

	@Scheduled(fixedDelay = 1,initialDelay = 100)
	public void sendMessage() {
		final Event event= new Event("application Self!","message body" );
		sendMessage(event);
	}




}
