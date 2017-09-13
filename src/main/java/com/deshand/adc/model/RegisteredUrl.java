package com.deshand.adc.model;

import java.net.URL;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RegisteredUrl {

	private final String id;
	private final URL url;

	public RegisteredUrl(String id, URL url) {
		this.id = id;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}

}
