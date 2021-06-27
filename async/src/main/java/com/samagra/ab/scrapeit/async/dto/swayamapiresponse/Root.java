package com.samagra.ab.scrapeit.async.dto.swayamapiresponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Root {

	Data data;

	@JsonIgnore
	Object errors;

}
