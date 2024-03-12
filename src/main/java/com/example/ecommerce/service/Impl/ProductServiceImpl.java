package com.example.ecommerce.service.Impl;


import com.example.ecommerce.entity.Product;

import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // METHOD -> SAVES A NEW PRODUCT TO THE DB.
    @Override
    public Product createProduct(Product product) {

        //saving the product to the database;
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }


    // METHOD -> GETS A  PRODUCT TO THE DB USING ID.
    @Override
    public Product getAProduct(Long id) {

        // Getting a single product from the DB
        try{
            Product product = productRepository
                    .findById(id)
                    .orElseThrow(()->new RuntimeException("There is no Product"));

            return product;

        }catch (Exception e){
            return new Product();
        }
    }


    // METHOD -> GETS All PRODUCTS FROM THE DB.
    @Override
    public List<Product> getProducts() {

        List<Product> products = productRepository.findAll();
        return products;
    }


    //METHOD -> UPDATE A PRODUCT
    @Override
    public Product updateAProduct(Long id, Product product) {


        // Getting a single product from the DB
        try{
            Product productDetails = productRepository
                    .findById(id)
                    .orElseThrow(()->new RuntimeException("There is no Product"));


            //updating the product details-----------
            productDetails.setProductDescription(product.getProductDescription());
            productDetails.setProductName(product.getProductName());
            productDetails.setProductManufacturer(product.getProductManufacturer());
            productDetails.setPrice(product.getPrice());
            productDetails.setQuantity(product.getQuantity());

            //saving the changes in the product
            Product updatedProduct = productRepository.save(productDetails);
            return updatedProduct;

        }catch (Exception e){
            return new Product();
        }
    }

    @Override
    public void deleteAProduct(Long id) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("There is no Product with the id: " + id));

        productRepository.deleteById(id);
    }


    @Override
    public List<Product> getProductGreaterThanPrice(double price) {
//        try{
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return
//        }
        return productRepository.getProductGreaterThanPrice(price);
    }

    @Override
    public List<Product> getProductOfManufacturer(String manufacturer) {
        return productRepository.getProductOfManufacturer(manufacturer);
    }

    @Override
    public List<Product> getProductsBetweenPrice(double minPrice, double maxPrice) {
        return productRepository.getProductBetweenPrice(minPrice, maxPrice);
    }
}
