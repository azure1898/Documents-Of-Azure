package com.zhiyou100.service;

import com.zhiyou100.mapper.UserMapper;
import com.zhiyou100.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lijike 2016/12/5.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int deleteByPrimaryKey(Integer id){
       return userMapper.deleteByPrimaryKey(id);
    }

    public int insert(User user){
        return userMapper.insert(user);
    }

    public User selectByPrimaryKey(Integer id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    public int updateByPrimaryKey(User user){
        return userMapper.updateByPrimaryKey(user);
    }
}
