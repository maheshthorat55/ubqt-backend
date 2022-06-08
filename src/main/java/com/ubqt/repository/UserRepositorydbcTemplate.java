package com.ubqt.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ubqt.model.SearchSkill;

@Repository
public class UserRepositorydbcTemplate {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	 
	public List<Long> findAllUserIds(List<SearchSkill> searchSkills) throws DataAccessException {
		
        String sql = "select user_id from (SELECT user_id, count(user_id) as user_count FROM ubqt.skill_evaluation where ";
        int count = 0;
        for (SearchSkill searchSkill : searchSkills) {
        	if(count > 0) {
        		sql += "or (skill_id = " + searchSkill.getSkillId() + " and evaluation >= " + searchSkill.getExperties() + ") ";
        	} else {
        		sql += "(skill_id = " + searchSkill.getSkillId() + " and evaluation >= " + searchSkill.getExperties() + ") ";
        	}
        	count++;
		}
        sql += "group by user_id) u where user_count = "+ count;
        		
        List<Long> customers = jdbcTemplate.query(
			        sql,
			        (rs, rowNum) -> new Long(rs.getLong("user_id")));

        return customers;
		
    }

}
