package com.deshand.adc.service;

import java.util.List;

import com.deshand.adc.model.RegisteredUrl;

public interface IUrlService {
	
	public void store(String id, String url);
	
	public RegisteredUrl getById(String id);
	
	public List <String> getAll();

}
