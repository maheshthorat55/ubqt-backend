package com.ubqt.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TalentMap {
	private Long streamId;
	List<CategoryResponse> categories;
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class CategoryResponse implements Comparable<CategoryResponse> {
		private Long id;
		private String name;
		private int position;
		List<SkillResponse> skills;
		@Override
		public int compareTo(CategoryResponse o) {
			return this.position - o.position;
		}
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SkillResponse {
		private Long id;
		private int position;
		private String name;
		private String shortName;
		private String aliesName;
		private Long rating;
	}
	
}
