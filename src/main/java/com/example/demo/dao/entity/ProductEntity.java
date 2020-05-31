package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="c_product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length=20,name="productname")
    private String productName;

    @Column
    private Integer productPrice;

    @Column
    private Date putawayDate;

    @Column
    private Integer stock;

    @Column
    private Integer sending;

}
