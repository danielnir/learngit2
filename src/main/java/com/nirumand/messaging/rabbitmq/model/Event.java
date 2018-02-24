package com.nirumand.messaging.rabbitmq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class Event implements Serializable {
	private static final Logger log = LoggerFactory.getLogger(Event.class);

	@JsonProperty
	private String source;

	@JsonProperty
	private String message;

	public Event() {

	}

	public Event(String source, String message) {
		this.source = source;
		this.message = message;
	}

	public String getSource() {
		return source;
	}

	public Event setSource(String source) {
		this.source = source;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Event setMessage(String message) {
		this.message = message;
		return this;
	}

	@Override
	public String toString() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("Can not convert to String",e);
			return null;
		}
	}

}
