package com.likelion.dao;

import com.likelion.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private ConnectionMaker cm;

    public UserDao() {
        this.cm = new LocalConnectionMaker();
    }

    public UserDao(ConnectionMaker cm) {
        this.cm = cm;
    }

    public void insert(User user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = cm.makeConnection();

            String insertQuery = "INSERT INTO user(id, name, password) VALUES(?,?,?);";
            ps = conn.prepareStatement(insertQuery);
            ps.setString(1,user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.executeUpdate(); // insert 는 테이블에 영향을 주기 때문에 Update
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
        System.out.println("INSERT 성공");
    }

    public User select(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = cm.makeConnection();

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
        System.out.println("SELECT 성공");
    }
    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = cm.makeConnection();

            String deleteQuery = "DELETE from user;";
            ps = conn.prepareStatement(deleteQuery);
            ps.executeUpdate(); // delete 는 테이블에 영향을 주기 때문에 Update
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
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
        System.out.println("DELETE All 성공");
    }
    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            count = 0;
            conn = cm.makeConnection();
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