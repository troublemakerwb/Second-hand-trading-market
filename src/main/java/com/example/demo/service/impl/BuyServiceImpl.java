package com.example.demo.service.impl;

import com.example.demo.controller.param.BuyReq;
import com.example.demo.dao.entity.BuyEntity;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repo.BuyRepository;
import com.example.demo.dao.repo.ProductRepository;
import com.example.demo.domain.Buy;
import com.example.demo.service.BuyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuyServiceImpl implements BuyService {
    
   @Autowired
   private BuyRepository buyRepository;

   @Autowired
   private ProductRepository productRepository;
    
    @Override
    public Buy createOne(BuyReq buyReq) {
        BuyEntity buyEntity = new BuyEntity();
        Buy buy = new Buy();
        BeanUtils.copyProperties(buyReq,buyEntity);
       // buyEntity.setProductEntities(productEntities);
        BuyEntity find = new BuyEntity();
        find = this.buyRepository.findByIdLikeRawSQL(buyEntity.getProductId());
        if(find!=null){
            this.buyRepository.updateProductNum(find.getProductId());
            BeanUtils.copyProperties(find,buy);
            return buy;
        }
        buyEntity = this.buyRepository.save(buyEntity);
        BeanUtils.copyProperties(buyEntity,buy);
        return buy;
    }

    @Override
    public List<Buy> getAll() {
        List<BuyEntity> buyEntities = this.buyRepository.findAll();
        List<Buy> buys = buyEntities.stream().map(entity ->{
            Buy buy = new Buy();
            String productName = this.productRepository.findNameByIdLikeRawSQL(entity.getProductId());
            buy.setProductName(productName);
            BeanUtils.copyProperties(entity,buy);
           // System.out.println(buy.toString());
            return buy;
        }).collect(Collectors.toList());

        return buys;
    }

    @Override
    public void deleteBuy(Long id) {
        this.buyRepository.deleteBuyEntityById(id);
    }

    @Override
    public Buy editBuy(Long id,Integer productNum,Integer checked) {
        BuyEntity buyEntity = new BuyEntity();
        this.buyRepository.updateProductNumLikeRawSQL(id,productNum);
        this.buyRepository.updateProductCheckedLikeRawSQL(id,checked);
        buyEntity = this.buyRepository.findByIdLikeRawSQL(id);
        Buy buy = new Buy();
        BeanUtils.copyProperties(buyEntity,buy);
        return buy;
    }

    @Override
    public List<Buy> findBuys(String keyname) {
        return null;
    }

    @Override
    public Buy findBuyById(Long buyId) {
        return null;
    }

    @Override
    public List<Buy> findRecords(Long userId) {
        List<BuyEntity> buyEntities = this.buyRepository.findByUserIdLikeRawSQL(userId);
        List<Buy> buys = buyEntities.stream().map(entity ->{
            Buy buy = new Buy();
            String productName = this.productRepository.findNameByIdLikeRawSQL(entity.getProductId());
            buy.setProductName(productName);
            BeanUtils.copyProperties(entity,buy);
            // System.out.println(buy.toString());
            return buy;
        }).collect(Collectors.toList());

        return buys;
    }
}
