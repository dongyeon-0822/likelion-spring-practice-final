package com.likelion.dao;

import com.likelion.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            ps = stmt.makePreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void insert(User user) throws SQLException, ClassNotFoundException {
        AddStrategy addStrategy = new AddStrategy(user);
        jdbcContextWithStatementStrategy(addStrategy);
        System.out.println("INSERT 성공");
    }
    public User select(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = dataSource.getConnection();

            String selectQuery = "SELECT * from user WHERE id=?;";
            ps = conn.prepareStatement(selectQuery);
            ps.setString(1,id);
            rs = ps.executeQuery();
            rs.next();
            user = new User(rs.getString(1), rs.getString(2),rs.getString(3));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void deleteAll() throws SQLException, ClassNotFoundException {
        DeleteStategy deleteStategy = new DeleteStategy();
        jdbcContextWithStatementStrategy(deleteStategy);
        System.out.println("DELETE ALL 성공");
    }
    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            count = 0;
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT count(*) from user;");
            rs = ps.executeQuery();
            rs.next();
            count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
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

//    public List<User> selectAll() throws SQLException, ClassNotFoundException {
//        Connection conn = cm.makeConnection();
//
//        String selectQuery = "SELECT * from user WHERE;";
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(selectQuery);
//
//        ArrayList<User> userList = new ArrayList<User>();
//
//        while(rs.next()) {
//            User user = new User();
//            user.setId(rs.getString(1));
//            user.setName(rs.getString(2));
//            user.setPassword(rs.getString(3));
//            userList.add(user);
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
//        System.out.println("SELECT All 성공");
//        return userList;
//    }
}