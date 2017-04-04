package org.dynamo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynamo.entity.ShoppingCartAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartAnswerDaoImpl implements ShoppingCartAnswerDao {
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public ShoppingCartAnswer getAnswerByUserId(long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		
		String sql = "select * from shopping_cart_answers where user_id=:userId";        
		List<ShoppingCartAnswer> answers = namedParameterJdbcTemplate.query(sql, params, new AnswerMapper());
		return answers.size() == 0 ? null : answers.get(0);
	}
	
	@Override
	public long saveAnswer(ShoppingCartAnswer answer) {
 		Map<String, Object> params = new HashMap<String, Object>(); 		
 		String sql = "";
 		
 		params.put("submitted", answer.isSubmitted());
		params.put("bugs", answer.getBugs());
		params.put("ideas", answer.getIdeas());
		params.put("improvements", answer.getImprovements());
		params.put("user_id", answer.getUserId());
		
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("submitted", answer.isSubmitted());
        namedParameters.addValue("bugs", answer.getBugs());
        namedParameters.addValue("ideas", answer.getIdeas());
        namedParameters.addValue("improvements", answer.getImprovements());
        namedParameters.addValue("user_id", answer.getUserId());
		
		if(answer.getId() == 0) {
			sql = "insert into shopping_cart_answers (submitted,bugs,ideas,improvements,user_id) values(:submitted,:bugs,:ideas,:improvements,:user_id)";
			namedParameterJdbcTemplate.update(sql, namedParameters, generatedKeyHolder, new String[] { "id" });
			long answerId = generatedKeyHolder.getKey().longValue();
	        return answerId;
		} else {
			namedParameters.addValue("id", answer.getId());
			sql = "update shopping_cart_answers set submitted=:submitted, bugs=:bugs, ideas=:ideas, improvements=:improvements WHERE id=:id";
			namedParameterJdbcTemplate.update(sql, namedParameters);
			return answer.getId();
		}
	}
	
	private final class AnswerMapper implements RowMapper<ShoppingCartAnswer> {

		@Override
		public ShoppingCartAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
			ShoppingCartAnswer answer = new ShoppingCartAnswer();
			answer.setBugs(rs.getString("bugs"));
			answer.setIdeas(rs.getString("ideas"));
			answer.setImprovements(rs.getString("improvements"));
			answer.setId(rs.getLong("id"));
			answer.setSubmitted(rs.getBoolean("submitted"));
			answer.setUserId(rs.getLong("user_id"));
			return answer;

		}		
	}



}
