package com.example.ecommerce.service.Impl;


import com.example.ecommerce.entity.Product;

import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    //VARIABLES
    ProductRepository productRepository;

    //CONSTRUCTER
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //
    //
    //
    // METHOD -> SAVES A NEW PRODUCT TO THE DB.
    @Override
    public Product createProduct(Product product) {

        //saving the product to the database;
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    //
    //
    //
    // METHOD -> GETS A  PRODUCT TO THE DB USING ID.
    @Override
    public Product getAProduct(Long id) {

        // Getting a single product from the DB
        try{
            Product product = productRepository
                    .findById(id)
                    .orElseThrow(()->new RuntimeException("There is not Product with Id : " + id));

            return product;

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    //
    //
    //
    // METHOD -> GETS All PRODUCTS FROM THE DB.
    @Override
    public List<Product> getProducts() {

        List<Product> products = productRepository.findAll();
        return products;
    }

    //
    //
    //
    //METHOD -> UPDATE A PRODUCT
    @Override
    public Product updateAProduct(Long id, Product product) {


        // Getting a single product from the DB
        try{
            Product productDetails = productRepository
                    .findById(id)
                    .orElseThrow(()->new RuntimeException("There is not Product with Id : " + id));


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
            throw new RuntimeException(e.getMessage());
        }
    }


    //
    //
    //
    // METHOD TO DELETE A PRODUCT
    @Override
    public void deleteAProduct(Long id) {

        try{
            Product product = productRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("There is no Product with the id: " + id));

            productRepository.deleteById(id);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


    //
    //
    //
    // METHOD TO GET ALL PRODUCT WHOSE PRICE ARE GREATER THAN CERTAIN AMOUNT
    @Override
    public List<Product> getProductGreaterThanPrice(double price) {
        try{
            return productRepository.getProductGreaterThanPrice(price);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    //
    //
    //
    // METHOD TO GET ALL PRODUCT WHICH IS MANUFACTURED BY A CERTAIN MANUFACTURER
    @Override
    public List<Product> getProductOfManufacturer(String manufacturer) {
        try{
            return productRepository.getProductOfManufacturer(manufacturer);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    //
    //
    //
    // METHOD TO GET ALL PRODUCT WHOSE PRICE ARE BETWEEN TWO RANGE
    @Override
    public List<Product> getProductsBetweenPrice(double minPrice, double maxPrice) {
        try{
            return productRepository.getProductBetweenPrice(minPrice, maxPrice);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


    //END BRACKET
}
