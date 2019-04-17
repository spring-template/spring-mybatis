package org.bytewen.mapper;

import org.bytewen.ApplicationStarter;
import org.bytewen.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * 增加用户
     */
    @Test
    public void add(){
        User user=new User();
        user.setUsername("李思思");
        user.setCityId("1");
        user.setPassword("lisisi");
        userMapper.add(user);
    }
    /**
     * 删除用户
     */
    @Test
    public void delete(){
        userMapper.delete(8l);
    }
    /**
     * 更新用户
     */
    @Test
    public void update(){
        User user=new User();
        user.setId(9l);
        user.setUsername("李孝利");
        userMapper.update(user);
    }
    /**
     * 获得用户
     */
    @Test
    public void get(){
        User user = userMapper.get(9l);
        System.out.println(user.getUsername());
    }
    /**
     * 条件查询用户
     */
    @Test
    public void getList(){
        User user=new User();
        user.setUsername("wen");
        List<User> list = userMapper.getList(user);
        System.out.println(list.size());
    }
    /**
     * 条件分页查询用户
     */
    @Test
    public void getListByPage(){
        User user=new User();
        user.setUsername("wen");
        List<User> users = userMapper.listByPage(3, 3,user.getUsername(),user.getWife());
        for (User u : users) {
            System.out.println(u.getUsername());
        }
    }
}
