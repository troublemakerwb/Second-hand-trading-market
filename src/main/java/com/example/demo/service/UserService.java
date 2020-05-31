package com.example.demo.service;

import com.example.demo.controller.param.UserReq;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public User createOne(UserReq userReq);

    public User updateUserName(String username,Long id);

    public List<User> getAll();

    public void deleteUser(Long id);

    public List<User> findUsers(String keyname);

    public List<User> findUsersByQuery(String keyname);

    public List<User> findUsersByRawQuery(String keyname);

  //  public List<User> findUsersByAges(Integer startAge,Integer startAge);

    public void updateUser(String username,Long id);

    public User findUser(String keyname);
}
