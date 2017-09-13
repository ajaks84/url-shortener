package com.deshand.adc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deshand.adc.dao.IUrlDao;
import com.deshand.adc.model.RegisteredUrl;
import com.deshand.adc.service.IUrlService;

@Service
public class MongoUrlService implements IUrlService {

	@Autowired
	private IUrlDao dao;

	@Override
	public void store(String id, String url) {
		dao.store(id, url);
	}

	@Override
	public RegisteredUrl getById(String id) {
		return dao.getById(id);
	}

	@Override
	public List<String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
