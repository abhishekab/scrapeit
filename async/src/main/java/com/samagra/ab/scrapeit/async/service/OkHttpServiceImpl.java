package com.samagra.ab.scrapeit.async.service;

import java.io.IOException;

import com.samagra.ab.scrapeit.async.exceptions.custom.UnsuccessfulGetException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.stereotype.Service;

@Service
public class OkHttpServiceImpl implements HttpService {

	private final OkHttpClient httpClient = new OkHttpClient();

	@Override
	public String doGetRequest(String url) throws IOException, UnsuccessfulGetException {
		Request request = new Request.Builder().url(url).build();
		Call call = this.httpClient.newCall(request);
		Response response = call.execute();
		if (!response.isSuccessful()) {
			throw new UnsuccessfulGetException();
		}
		return response.body().string();
	}

}
