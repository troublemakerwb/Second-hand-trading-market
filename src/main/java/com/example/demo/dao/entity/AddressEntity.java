package com.example.demo.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="c_address")
public class AddressEntity {
    @Column
    private String userName;

    @Column
    private String streetName;

    @Column
    private String tel;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long addressId;

    @Column
    private Integer isDefault;
}
