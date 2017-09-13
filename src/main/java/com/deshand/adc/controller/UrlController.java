package com.deshand.adc.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deshand.adc.service.IUrlService;
import com.google.common.hash.Hashing;

@RestController
public class UrlController {
	
	@Autowired
	IUrlService iUrlService;

	@RequestMapping(value = "/url", method = RequestMethod.POST)
	public ResponseEntity<?> postUrl(@RequestBody String url, BindingResult bindingResult) {

		if (!isUrlValid(url)) {
			bindingResult.addError(new ObjectError("url", "Invalid url format: " + url));
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST); 
		}

		if (!bindingResult.hasErrors()) {
			final String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
			iUrlService.store(id, url);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED); 
	}
	
	@RequestMapping(value = "/url/{id}", method = RequestMethod.GET)
    public void redirectToUrl(@PathVariable String id, HttpServletResponse resp) throws Exception {
        final String url = iUrlService.getById(id).getUrl().toString();
        if (url != null) {
            resp.addHeader("Location", url);
            resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
	
	
	private boolean isUrlValid(String url) {
		boolean valid = true;
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			valid = false;
		}
		return valid;
	}

}
