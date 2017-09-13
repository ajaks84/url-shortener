package com.deshand.adc.dao.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.deshand.adc.dao.IUrlDao;
import com.deshand.adc.exception.UnknownShortenedUrlException;
import com.deshand.adc.model.RegisteredUrl;

@Repository
public class MongoUrlDao implements IUrlDao {

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public void store(String id, String url) {
		System.out.println("Link has been strored. Id is: " + id + " Url is: " + url);
		RegisteredUrl regUrl=null;
		try {
			regUrl = new RegisteredUrl(id, new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}if(regUrl!=null)
		mongoOps.save(regUrl);

	}

	@Override
	public RegisteredUrl getById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
        RegisteredUrl result = this.mongoOps.findOne(query, RegisteredUrl.class);
        if (result == null) {
            throw new UnknownShortenedUrlException(id);
        }
       
        return result;
	}

	@Override
	public List<String> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
