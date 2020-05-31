package com.example.demo.controller;

import com.example.demo.controller.param.UserReq;
import com.example.demo.domain.ResultData;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/exception")
    public User getError() throws Exception{
        throw new Exception("My Exception");
    }

    @CrossOrigin
    @PostMapping("/api/login")
    @ResponseBody
    public ResultData login(@RequestBody UserReq userReq){
        User loginUser = new User();
       // System.out.println(userReq.getUserName());
        BeanUtils.copyProperties(userReq,loginUser);
        String userName = loginUser.getUserName();
        String userPwd = loginUser.getUserPwd();
      //  System.out.println(userName);
      //  System.out.println(userPwd);
        User user = this.userService.findUser(userName);
        System.out.println(user.getRanks());
        if(user != null){
            if(user.getUserPwd().equals(userPwd)){
                log.info("登录成功！");
            }else {
                return ResultData.fail("密码错误！");
            }
        }else{
            return ResultData.fail("用户不存在！");
        }
        return ResultData.success(user.getRanks());
    }

   // @CrossOrigin
    @PostMapping("/api/user/select")
    @ResponseBody
    public String selectUser(@RequestBody UserReq userReq){
        User registerUser = new User();
        BeanUtils.copyProperties(userReq,registerUser);
        String username = registerUser.getUserName();
        User user = this.userService.findUser(username);
        if(user == null){
            return "0";
        }
        return "1";
    }

   // @CrossOrigin
    @PostMapping("/api/register")
    @ResponseBody
    public String addUser(@RequestBody UserReq userReq){
        userReq.setRanks(0);
        User user = this.userService.createOne(userReq);
        System.out.println(user.getUserName());
        System.out.println(user.getUserPwd());
        System.out.println(user.getRanks());
        return "1";
    }



    @GetMapping("/users")
    public List<User> getUsers(){
        return this.userService.getAll();
    }

    @PostMapping("/users/form")
    public User createUser(UserReq userReq){
        log.info("Use Req in form:{}",userReq);
        User user = this.userService.createOne(userReq);
        log.info("Create User:{}",user);
        return user;
    }

    @PostMapping("/users/json")
    //@RequestMapping(value = "/user/json",method = RequestMethod.POST)
    public User createUserJson(@RequestBody UserReq userReq){
        log.info("User Req in Json:{}",userReq);
        User user  = new User();
        BeanUtils.copyProperties(userReq,user);
        log.info("Create User:{}",user);
        user.setId(1000L);
        return user;

    }

    @Transactional
    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id,String username){
        log.info("User Update:{}, username:{}", id, username);
        this.userService.updateUser(username,id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        log.info("Delete User:{}",id);
        this.userService.deleteUser(id);
    }

    @GetMapping("/users/name")
    public List<User> getUsersByName(@RequestParam("keyname")String keyname){
        List<User> users = this.userService.findUsers("%" + keyname +"%");
        log.info("KeyName:{},users:{}",keyname,users);
        return users;
    }

    @GetMapping("/users/name/query")
    public List<User> getUserByNameQuery(@RequestParam("keyname")String keyname){
        List<User> users = this.userService.findUsersByQuery("%" + keyname + "%");
        log.info("KeyName:{},users:{}",keyname,users);
        return users;
    }

    @GetMapping("/users/name/raw")
    public List<User> getUserByNameRaw(@RequestParam("keyname")String keyname){
        List<User> users = this.userService.findUsersByRawQuery("%" + keyname + "%");
        log.info("KeyName:{},users:{}",keyname,users);
        return users;
    }

    @PostMapping("/Userdel")
    @ResponseBody
    public ResultData deleteUser(@RequestBody String userId) throws JSONException {
        JSONArray jsonArray = null;
        jsonArray = new JSONArray("["+userId+"]");
        System.out.println(jsonArray.getJSONObject(0).get("userId"));
        String realId = jsonArray.getJSONObject(0).get("userId").toString();
        Long id = Long.valueOf(realId);

        this.userService.deleteUser(Long.valueOf(realId));
        return ResultData.success();
    }

}
