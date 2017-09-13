package com.deshand.adc.dao;

import java.util.List;

import com.deshand.adc.model.RegisteredUrl;

public interface IUrlDao {
	
	public void store(String id, String url);

	public RegisteredUrl getById(String id);

	public List<String> getAll();

}
