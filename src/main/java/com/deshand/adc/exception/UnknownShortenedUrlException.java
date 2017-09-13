package com.deshand.adc.exception;

import java.net.URL;

public class UnknownShortenedUrlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownShortenedUrlException(String id) {
        super("Unknown URL with ID: " + id);
    }

    public UnknownShortenedUrlException(URL url) {
        super("Unknown URL: " + url);
    }
}
