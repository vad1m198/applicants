package org.dynamo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dynamo.entity.User;
import org.dynamo.entity.UserRole;
import org.dynamo.model.AuthenticationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", username);
        
        String sql = "select users.id as userid, users.first_name as first_name, users.second_name as second_name, "
						+ "users.email as email, users.password as password, user_roles.id as roleid, user_roles.role as role " 
				        + "from users " 
						+ "left join user_user_role ON users.id = user_user_role.user_id " 
					    + "left join user_roles ON user_roles.id = user_user_role.role_id "
					    + "where users.email=:email";
        Map<Long, User> map = namedParameterJdbcTemplate.query(sql, params, new UserWithRolesExtractor());
        User user = null;
		
		if(!map.isEmpty()) {
			user = new ArrayList<>(map.values()).get(0);
		}

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }

        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();        
        for(UserRole role: user.getRoles()) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            grantList.add(authority);
        }
        
        AuthenticationUser customUser = new AuthenticationUser(user.getFirstName() + " " + user.getSecondName(),
                user.getPassword(),grantList, user.getId());
        return customUser;		
	}

	@Override
	public List<User> getAllUsers() {
		Map<String, Object> params = new HashMap<String, Object>();
        String sql = "select users.id as userid, users.first_name as first_name, users.second_name as second_name, "
						+ "users.email as email, users.password as password, user_roles.id as roleid, user_roles.role as role " 
				        + "from users " 
						+ "left join user_user_role ON users.id = user_user_role.user_id " 
					    + "left join user_roles ON user_roles.id = user_user_role.role_id";                
        Map<Long, User> map = namedParameterJdbcTemplate.query(sql, params, new UserWithRolesExtractor());
        return new ArrayList<>(map.values());
	}
	
	public List<User> getAllUsersByFnameAndSname(String firstName, String secondName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("firstName", "%"+firstName.toLowerCase()+"%");
		params.put("secondName", "%"+secondName.toLowerCase()+"%");
		String sql = "select users.id as userid, users.first_name as first_name, users.second_name as second_name, "
				+ "users.email as email, users.password as password, user_roles.id as roleid, user_roles.role as role " 
		        + "from users " 
				+ "left join user_user_role ON users.id = user_user_role.user_id " 
			    + "left join user_roles ON user_roles.id = user_user_role.role_id "
			    + "where LOWER(first_name) like :firstName and LOWER(second_name) like :secondName";

		Map<Long, User> map = namedParameterJdbcTemplate.query(sql, params, new UserWithRolesExtractor());
        return new ArrayList<>(map.values());
	}
	
	@Override
	public User findUserById(long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
        String sql = "select users.id as userid, users.first_name as first_name, users.second_name as second_name, "
						+ "users.email as email, users.password as password, user_roles.id as roleid, user_roles.role as role " 
				        + "from users " 
						+ "left join user_user_role ON users.id = user_user_role.user_id " 
					    + "left join user_roles ON user_roles.id = user_user_role.role_id "
					    + "where users.id=:id";
		        
        Map<Long, User> map = namedParameterJdbcTemplate.query(sql, params, new UserWithRolesExtractor());
        
        return map.get(id);
	}
	
	@Override
	public long updateUser(long id, User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fname", user.getFirstName());
		params.put("sname", user.getSecondName());
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
		params.put("userId", id);
		
		String sql = "update users set first_name=:fname, second_name=:sname, email=:email, password=:password where id=:userId";	
		namedParameterJdbcTemplate.update(sql, params);
		
		String deletesql = "delete from user_user_role where user_id=:userId ";	
		namedParameterJdbcTemplate.update(deletesql, params);
		
		String insertsql = "INSERT INTO user_user_role VALUES ";
		List<UserRole> userRoles = user.getRoles();
		for(int i = 0; i < userRoles.size(); i++) {
			insertsql += "(:userId," + userRoles.get(i).getId() + ")";			
			if(i != userRoles.size() - 1) {
				insertsql +=",";
			}
		}
			
		namedParameterJdbcTemplate.update(insertsql, params);
		return id;
	}
	

	@Override
	public long createUser(User user) {
		GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("fname", user.getFirstName());
        namedParameters.addValue("sname", user.getSecondName());
        namedParameters.addValue("email", user.getEmail());
        namedParameters.addValue("password", user.getPassword());
                
        String sql = "insert into users (first_name,second_name,email,password) values(:fname, :sname, :email, :password)";

        namedParameterJdbcTemplate.update(sql, namedParameters, generatedKeyHolder, new String[] { "id" });
        
        long userId = generatedKeyHolder.getKey().longValue();
        namedParameters.addValue("userId", userId);
				
		String insertsql = "INSERT INTO user_user_role VALUES ";
		List<UserRole> userRoles = user.getRoles();
		for(int i = 0; i < userRoles.size(); i++) {
			insertsql += "(:userId," + userRoles.get(i).getId() + ")";			
			if(i != userRoles.size() - 1) {
				insertsql +=",";
			}
		}
		namedParameterJdbcTemplate.update(insertsql, namedParameters);
		return userId;
	}
	
    private static final class UserWithRolesExtractor implements ResultSetExtractor<Map<Long, User>> {

        @Override
        public Map<Long, User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Long, User> mapEmailToUser = new HashMap<>();
            while(resultSet.next()){
            	long userid = resultSet.getLong("userid");
                if(mapEmailToUser.get(userid) == null) {
                    User user = new User();
                    user.setId(userid);
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setSecondName(resultSet.getString("second_name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    List<UserRole> roles = new ArrayList<>();
                    UserRole role = new UserRole();
                    role.setId(resultSet.getInt("roleid"));
                    role.setRole(resultSet.getString("role"));
                    roles.add(role);
                    user.setRoles(roles);
                    mapEmailToUser.put(userid, user);
                } else {
                    User user = mapEmailToUser.get(userid);
                    List<UserRole> roles = user.getRoles();
                    UserRole role = new UserRole();
                    role.setRole(resultSet.getString("role"));
                    role.setId(resultSet.getInt("roleid"));
                    roles.add(role);
                }
            }
            return mapEmailToUser;
        }
    }
}

