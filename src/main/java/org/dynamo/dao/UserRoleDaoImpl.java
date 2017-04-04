package org.dynamo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynamo.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<UserRole> getAllRoles() {
		Map<String, Object> params = new HashMap<String, Object>();
        String sql = "select * from user_roles";        
        return namedParameterJdbcTemplate.query(sql, params, new UserRoleMapper());
	}
	
	
	 private static final class UserRoleMapper implements RowMapper<UserRole> {
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserRole role = new UserRole();
			role.setId(rs.getInt("id"));
			role.setRole(rs.getString("role"));
			return role;
		}
	 }

}
