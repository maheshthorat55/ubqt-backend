package com.ubqt.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {
	private List<SearchSkill> skills;
	@Builder.Default
	private int assessed=0;
	@Builder.Default
	private int isAvailable=0;
}
