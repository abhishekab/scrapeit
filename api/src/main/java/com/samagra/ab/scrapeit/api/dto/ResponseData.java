package com.samagra.ab.scrapeit.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseData<T> {

	private boolean success;

	private T data;

	private String error;

}
