package com.example.ecommerce.service;


import com.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product getAProduct(Long id);

    List<Product> getProducts();

    Product updateAProduct(Long id, Product product);

    void deleteAProduct(Long id);

    List<Product> getProductGreaterThanPrice(double price);

    List<Product> getProductOfManufacturer(String manufacturer);

    List<Product> getProductsBetweenPrice(double minPrice,double maxPrice);
}
