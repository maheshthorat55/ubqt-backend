package com.ubqt.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ubqt.model.SkillSupply;

@Repository
public class SkillEvaluationRepositoryJdbcTemplate {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	 
	public Map<Long, Long> findSkillSupplies() throws DataAccessException {
        String sql = "select skill_id, count(skill_id) as supplyCount FROM skill_evaluation where evaluation > 0 group by skill_id";
        List<SkillSupply> skills = jdbcTemplate.query(
			        sql,
			        (rs, rowNum) -> SkillSupply.builder()
			        						.skillId(rs.getLong("skill_id"))
			        						.skillSupplyCount(rs.getLong("supplyCount"))
			        						.build());
        return skills.stream().collect(
                Collectors.toMap(x -> x.getSkillId(), x -> x.getSkillSupplyCount()));
		
    }
}
