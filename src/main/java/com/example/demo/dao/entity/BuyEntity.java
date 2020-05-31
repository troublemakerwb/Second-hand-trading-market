package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="c_buy")
public class BuyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "productid")
    private Long productId;

    @Column(name = "userid")
    private Long userId;

    @Column(name="productPrice")
    private Integer productPrice;

    @Column
    private Date buyDate;

    @Column
    private Integer checked;

    @Column
    private Integer productNum;




}
