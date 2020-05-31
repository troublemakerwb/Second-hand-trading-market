package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping(value = "/home/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> hello(
            @PathVariable("id")Integer id, @RequestParam(defaultValue="hello, Jack", required=false, value="keyname")String key){
        log.info("Incoming Request:{}, Id:{}",key,id);
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("request id",id.toString());
        dataMap.put("info-key",key);
        return dataMap;
    }


}
