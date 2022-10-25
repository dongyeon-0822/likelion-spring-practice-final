package com.likelion.dao;

import com.likelion.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("id"), rs.getString("name"),
                    rs.getString("password"));
            return user;
        }
    };

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(User user) {
        this.jdbcTemplate.update("insert into user(id, name, password) values (?, ?, ?);",
                user.getId(), user.getName(), user.getPassword());
    }
    public User select(String id) {
        String sql = "Select * from user where id = ?";
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
    public void deleteAll() {
        this.jdbcTemplate.update("Delete from user");
    }
    public int getCount()  {
        return this.jdbcTemplate.queryForObject("select count(*) from users;", Integer.class);
    }
    public List<User> selectAll() throws SQLException {
        return this.jdbcTemplate.query("select * from order by id", rowMapper);
    }
//    public void delete(String id) throws SQLException, ClassNotFoundException {
//        Connection conn = cm.makeConnection();
//
//        String deleteQuery = "DELETE from user where id=?;";
//        PreparedStatement ps = conn.prepareStatement(deleteQuery);
//        ps.setString(1,id);
//
//        ps.executeUpdate(); // delete 는 테이블에 영향을 주기 때문에 Update
//        ps.close();
//        conn.close();
//        System.out.println("DELETE 성공");
//    }


}