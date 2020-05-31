package com.example.demo.service.impl;

import com.example.demo.dao.entity.AddressEntity;
import com.example.demo.dao.entity.BuyEntity;
import com.example.demo.dao.repo.AddressRepository;
import com.example.demo.domain.Address;
import com.example.demo.domain.Buy;
import com.example.demo.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> getAll() {
        List<AddressEntity> addressEntities = this.addressRepository.findAll();
        List<Address> addresses = addressEntities.stream().map(entity ->{
            Address address = new Address();
            BeanUtils.copyProperties(entity,address);
            // System.out.println(buy.toString());
            return address;
        }).collect(Collectors.toList());

        return addresses;
    }

    @Override
    public void setDefault(Long addressId) {
        this.addressRepository.updateIsDefault(addressId);
    }

    @Override
    public void deleteAddress(Long addressId) {
        this.addressRepository.deleteAddress(addressId);
    }

    @Override
    public void addAddress(String userName, String streetName, String tel) {
        this.addressRepository.addAddress(userName,streetName,tel);
    }
}
