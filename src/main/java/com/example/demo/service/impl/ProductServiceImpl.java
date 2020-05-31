package com.example.demo.service.impl;

import com.example.demo.controller.param.ProductReq;
import com.example.demo.dao.entity.ProductEntity;
import com.example.demo.dao.repo.BuyRepository;
import com.example.demo.dao.repo.ProductRepository;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BuyRepository buyRepository;

    @Override
    public Product createOne(ProductReq productReq) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productReq, productEntity);
        productEntity = this.productRepository.save(productEntity);
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

    @Override
    public Product updateProductName(String productname, Long id) {
        Optional<ProductEntity> productEntityOptional = this.productRepository.findById(id);
        Product product = new Product();
        if (productEntityOptional.isPresent()) {
            ProductEntity productEntity = productEntityOptional.get();
            productEntity.setProductName(productname);
            productEntity = this.productRepository.save(productEntity);
            BeanUtils.copyProperties(productEntity, product);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<ProductEntity> productEntities = this.productRepository.findAll();
        List<Product> products = productEntities.stream().map(entity -> {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public List<Product> search(ProductReq req) {
        List<ProductEntity> productEntities = this.productRepository.findAllByMulti(req.getProductName(), req.getMinPrice(), req.getMaxPrice());
        List<Product> products = productEntities.stream().map(entity -> {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProducts(String keyname) {
        List<ProductEntity> productEntities = this.productRepository.findByProductNameLike(keyname);
        List<Product> products = productEntities.stream().map(entity -> {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public List<Product> findProductsByQuery(String keyname) {
        List<ProductEntity> productEntities = this.productRepository.findByNameLike2(keyname);
        List<Product> products = productEntities.stream().map(entity -> {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public List<Product> findProductsByRawQuery(String keyname) {
        List<ProductEntity> productEntities = this.productRepository.findByNameLikeRawSQL(keyname);
        List<Product> products = productEntities.stream().map(entity -> {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public Product findProductByName(String productName) {
        ProductEntity productEntity = this.productRepository.findByNameUseRawSQL(productName);
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

    @Override
    public Product findProductById(Long productId) {
        ProductEntity productEntity = this.productRepository.findByIdLikeRawSQL(productId);
        Product product = new Product();
        BeanUtils.copyProperties(productEntity, product);
        return product;
    }

    @Override
    public void updateProduct(String productname, Long id) {
        this.productRepository.updateProductName(productname, id);
    }

    @Override
    public List<Product> sortProduct(Integer sort) {
        //Sort sort1 = new Sort(Sort.Direction.DESC, "product_price");
        List<ProductEntity> productEntities = new ArrayList<>();
        if (sort == 1) {
            productEntities = this.productRepository.findAll(Sort.by(Sort.Direction.ASC, "product_price"));
        } else if (sort == -1) {
            productEntities = this.productRepository.findAll(Sort.by(Sort.Direction.DESC, "product_price"));
        }
        List<Product> products = productEntities.stream().map(entity -> {
            Product product = new Product();
            BeanUtils.copyProperties(entity, product);
            return product;
        }).collect(Collectors.toList());
        return products;
    }

    @Override
    public void deleteProductAll(List<Long> ids) {
        buyRepository.findAllByIdIn(ids).stream().forEach(x -> {
            ProductEntity entity = productRepository.getOne(x.getProductId());
            if (entity != null) {
                entity.setSending(entity.getSending()+x.getProductNum());
                productRepository.save(entity);
            }
        });
        buyRepository.deleteAllByIdIn(ids);
    }
}
