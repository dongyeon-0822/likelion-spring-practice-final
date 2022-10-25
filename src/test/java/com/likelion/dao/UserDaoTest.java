package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    @Test
    void insertAndSelectTest() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();

        User user = new User("1", "Tom", "1234");

        userDao.insert(user);
        User selectedUser = userDao.select("1");
        Assertions.assertEquals(user.getName(), selectedUser.getName());
        Assertions.assertEquals(user.getPassword(), selectedUser.getPassword());
    }
}