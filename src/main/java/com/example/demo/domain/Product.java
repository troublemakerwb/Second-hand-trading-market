package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String productName;
    private Integer productPrice;
    private Date putawayDate; //商品上架日期
    private Integer stock; //商品库存量
    private Integer sending; //商品出货数量
    
}
