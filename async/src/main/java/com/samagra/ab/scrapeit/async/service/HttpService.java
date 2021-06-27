package com.samagra.ab.scrapeit.async.service;

import java.io.IOException;

import com.samagra.ab.scrapeit.async.exceptions.custom.UnsuccessfulGetException;

public interface HttpService {

	String doGetRequest(String url) throws IOException, UnsuccessfulGetException;

}
