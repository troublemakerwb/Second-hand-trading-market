package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String userName;

    private String streetName;

    private String tel;

    private Long addressId;

    private Integer isDefault;

}
