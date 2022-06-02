package com.ubqt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortListRequest {
	private Long userId;
	private Long shortListId;
}
