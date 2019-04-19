package org.bytewen.service;

import org.bytewen.mapper.UserHobbyMapper;
import org.bytewen.mapper.UserMapper;
import org.bytewen.po.User;
import org.bytewen.po.UserHobby;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserHobbyMapper userHobbyMapper;

    public void add(User user){
        userMapper.add(user);
    }
    public void update(User user){
        userMapper.update(user);
    }
    public User getByUsername(String username){
        return userMapper.getByUsername(username);
    }
    @Transactional
    public void register(User user,Long hobbyId){
        this.userMapper.add(user);
        UserHobby userHobby=new UserHobby();
        userHobby.setUserId(user.getId());
        if(hobbyId!=null){
            userHobby.setHobbyId(hobbyId);
            this.userHobbyMapper.add(userHobby);
        }
    }
    public User checkBySalt(String salt){
        User user = userMapper.getBySalt(salt);
        return user;
    }

}
