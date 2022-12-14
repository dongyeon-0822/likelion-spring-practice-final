package com.likelion.dao;

import com.likelion.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {
    @Autowired
    ApplicationContext context;

    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp() {
        this.userDao = context.getBean("awsUserDao", UserDao.class);
        this.user1 = new User("12", "Billy", "1234");
        this.user2 = new User("13", "sujin", "1234");
        this.user3 = new User("14", "sohyun", "1234");
    }

    @Test
    @DisplayName("insert And Select Test")
    void insertAndSelectTest() {
        userDao.deleteAll();
        userDao.insert(user1);
        User selectedUser = userDao.select(user1.getId());
        Assertions.assertEquals(user1.getName(), selectedUser.getName());
        Assertions.assertEquals(user1.getPassword(), selectedUser.getPassword());
    }

    @Test
    @DisplayName("delete and getCount Test")
    void deleteAndCountTest() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertEquals(0,userDao.getCount());

        userDao.insert(user1);
        userDao.insert(user2);
        userDao.insert(user3);
        assertEquals(3, userDao.getCount());
    }

    @Test
    @DisplayName("selectAll Test")
    public void selectAllTest() throws SQLException {
        userDao.deleteAll();
        List<User> users = new ArrayList<>();

        userDao.insert(user1);
        userDao.insert(user2);
        userDao.insert(user3);
        users = userDao.selectAll();
        assertEquals(3, users.size());
    }

    @Test
    void selectExceptionTest() {
        userDao.deleteAll();
        assertThrows(EmptyResultDataAccessException.class, ()->{
            userDao.select("30");
        });
    }
}