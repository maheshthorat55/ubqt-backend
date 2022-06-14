package com.ubqt.model;

import java.time.LocalDateTime;
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
		private String shortName;
		private int demand;
		private int position;
		private String color;
		List<SkillResponse> skills;
		@Override
		public int compareTo(CategoryResponse o) {
			return this.position-o.position;
		}
	}
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SkillResponse {
		private Long id;
		private int demand;
		private Long supply;
		private String name;
		private String shortName;
		private String aliesName;
		private String about;
		private Long rating;
		private String assessment;
		private String learnFrom;
		private String certificationStatus;
		private String color;
		private String textColor;
		private String [] colorCodes;
		private LocalDateTime lastAssessed;
		private Long experience;
	}
	
}
