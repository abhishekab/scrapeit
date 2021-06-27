package com.samagra.ab.scrapeit.api.dto;

import com.samagra.ab.scrapeit.api.dto.enums.SyncStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SyncResponse {

	private SyncStatus syncStatus;

}
