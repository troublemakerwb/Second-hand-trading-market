package com.example.demo.service;

import com.example.demo.domain.Address;
import com.example.demo.domain.Buy;

import java.util.List;

public interface AddressService {
    public List<Address> getAll();

    public void setDefault(Long addressId);

    public void deleteAddress(Long addressId);

    public void addAddress(String userName,String streetName,String tel);
}
