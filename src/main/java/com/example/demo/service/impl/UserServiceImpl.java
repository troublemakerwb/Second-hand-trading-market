package com.example.demo.service.impl;

import com.example.demo.controller.param.UserReq;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repo.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createOne(UserReq userReq) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userReq,userEntity);
        userEntity = this.userRepository.save(userEntity);
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);
        return user;
    }

    @Override
    public User updateUserName(String username, Long id) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findById(id);
        User user = new User();
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setUserName(username);
            userEntity = this.userRepository.save(userEntity);
            BeanUtils.copyProperties(userEntity,user);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        List<User> users = userEntities.stream().map(entity -> {
            User user = new User();
            BeanUtils.copyProperties(entity,user);
            return user;
            }).collect(Collectors.toList());
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> findUsers(String keyname) {
        List<UserEntity> entities = this.userRepository.findByUserNameLike(keyname);

        List<User> users = entities.stream().map(entity -> {
            User user = new User();
            BeanUtils.copyProperties(entity,user);
            return user;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public List<User> findUsersByQuery(String keyname) {
        List<UserEntity> entities = this.userRepository.findByNameLike2(keyname);

        List<User> users = entities.stream().map(entity -> {
            User user = new User();
            BeanUtils.copyProperties(entity,user);

            return user;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public List<User> findUsersByRawQuery(String keyname) {
        List<UserEntity> entities = this.userRepository.findByNameLikeRawSQL(keyname);

        List<User> users = entities.stream().map(entity ->{
            User user = new User();
            BeanUtils.copyProperties(entity,user);
            return user;
        }).collect(Collectors.toList());
        return users;
    }

    @Override
    public void updateUser(String username, Long id) {
        this.userRepository.updateUserName(username,id);

    }

    @Override
    public User findUser(String keyname) {
        UserEntity userEntity = this.userRepository.findByUserName(keyname);
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);
        return user;
    }
}
