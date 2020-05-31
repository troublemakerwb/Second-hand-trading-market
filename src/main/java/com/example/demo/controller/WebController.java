package com.example.demo.controller;


import com.example.demo.domain.WebSite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
public class WebController {

    @GetMapping("/dynamic/webjar")
    public String getWebJarSites(){
        log.info("Web Jar page loading...");
        return "webjarpage/site-serach";
    }

    @GetMapping("/dynamic/web")
    public String getDynamicSites(){
        log.info("dynamic page loading...");
        return "dynamic/site-search";
    }

    @RequestMapping(value="/dynamic/sites", method= RequestMethod.GET)
    @ResponseBody
    public List<WebSite> getSiteData(){
        List<WebSite> websites = new ArrayList<WebSite>();
        websites.add(new  WebSite("最大的中文搜索引擎,百度", "http://www.baidu.com", "/static/img/baidu-logo.jpeg"));
        websites.add(new WebSite("多快好省,一站式购物,京东", "http://www.jd.com", "/static/img/jd-logo.jpeg"));

        return websites;
    }

}
