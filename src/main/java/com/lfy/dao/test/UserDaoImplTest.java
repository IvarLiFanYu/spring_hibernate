package com.lfy.dao.test;

import com.lfy.dao.UserDao;
import com.lfy.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setAge(24);
        user.setName("lifanyu");
        userDao.saveUser(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testUpdate(){
        User user = new User();
        user.setUserId(3);
        user.setAge(1);
        user.setName("lifanyu");
        userDao.updateUser(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testFind(){
       User user = userDao.findById(3);
        System.out.println(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testDelete(){
        User user = new User();
        user.setUserId(3);
        userDao.deleteUser(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testfindUserByName(){
        List<User> userList = userDao.findUserByName("lifanyu");
        for (User user: userList){
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testfindUserByNameBySQL(){
        List<User> userList = userDao.findUserByNameBySQL("lifanyu");
        for (User user: userList){
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testfindUserByNameByQBC(){
        List<User> userList = userDao.findUserByNameByQBC("lifanyu");
        for (User user: userList){
            System.out.println(user);
        }
    }

}
