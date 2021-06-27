package com.samagra.ab.scrapeit.async.dto.swayamapiresponse;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseList {

	List<Edge> edges;

	PageInfo pageInfo;

}
