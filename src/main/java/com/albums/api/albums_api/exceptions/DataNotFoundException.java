package com.albums.api.albums_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException  {

	/**
	 *use this class when data is not found by id
	 */
	private static final long serialVersionUID = 1L;
    

    public DataNotFoundException(String message){
        super(message);
    }
}