package com.ubqt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShortListRequest {
	private Long clientId;
	private Long userId;
}
