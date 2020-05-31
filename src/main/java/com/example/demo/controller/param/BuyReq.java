package com.example.demo.controller.param;

import lombok.Data;

import java.util.Date;

@Data
public class BuyReq {
    private String username;
    private Long productId;
    private Long userId;
    private Integer productPrice;
    private Date buyDate; //商品购买日期
    private Integer checked;
    private Integer productNum; //商品购买数量
}
