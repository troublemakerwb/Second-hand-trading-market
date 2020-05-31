package com.example.demo.service;

import com.example.demo.controller.param.ProductReq;
import com.example.demo.domain.Product;

import java.util.List;

public interface ProductService {
    public Product createOne(ProductReq productReq);

    public Product updateProductName(String productname,Long id);

    public List<Product> getAll();

    public List<Product> search(ProductReq req);

    public void deleteProduct(Long id);

    public List<Product> findProducts(String keyname);

    public List<Product> findProductsByQuery(String keyname);

    public List<Product> findProductsByRawQuery(String keyname);

    public Product findProductById(Long productId);

    public Product findProductByName(String productName);

    public void updateProduct(String productname,Long id);

    public List<Product> sortProduct(Integer sort);

    void deleteProductAll(List<Long> ids);
}
