package com.example.demo.service;

import com.example.demo.controller.param.BuyReq;
import com.example.demo.domain.Buy;

import java.util.List;

public interface BuyService {
    public Buy createOne(BuyReq buyReq);

    public List<Buy> getAll();

    public void deleteBuy(Long id);

    public Buy editBuy(Long id,Integer productNum,Integer checked);

    public List<Buy> findBuys(String keyname);


    public Buy findBuyById(Long buyId);

    public List<Buy> findRecords(Long userId);

}
