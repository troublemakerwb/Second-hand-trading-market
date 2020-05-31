package com.example.demo.controller;

import com.example.demo.controller.param.BuyReq;
import com.example.demo.dao.entity.BuyEntity;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repo.BuyRepository;
import com.example.demo.dao.repo.ProductRepository;
import com.example.demo.dao.repo.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/addCart")
    public String addCart(@RequestBody BuyReq buyReq) {
        if (buyReq == null || StringUtils.isEmpty(buyReq.getUsername()) || buyReq.getProductId() == null) {
            return "缺少参数";
        }
        ProductEntity product = productRepository.getOne(buyReq.getProductId());
        if (product == null) return "没有此商品";
        if (product.getSending() < buyReq.getProductNum()) {
            return "最大只能下单" + product.getSending() + "件";
        }
        UserEntity user = userRepository.findByUserName(buyReq.getUsername());
        BuyEntity entity = new BuyEntity();
        BeanUtils.copyProperties(buyReq, entity);
        entity.setUserId(user.getId());
        entity.setProductId(buyReq.getProductId());
        entity.setBuyDate(new Date());
        entity.setChecked(1);
        product.setSending(product.getSending() - buyReq.getProductNum());
        if (buyRepository.save(entity) != null) {
            if (productRepository.save(product) != null) {
                return "加入购物车成功！";
            }
        }
        return "加入购物车失败";
    }

}
