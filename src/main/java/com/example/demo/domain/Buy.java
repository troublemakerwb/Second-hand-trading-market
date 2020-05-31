package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buy {
    private Long id;//订单id
    private Long productId;
    private Long userId;
    private String productName;
    private Integer productPrice;
    private Date buyDate; //商品购买日期
    private Integer checked;
    private Integer productNum; //商品购买数量

}
