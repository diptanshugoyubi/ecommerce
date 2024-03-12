package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //NATIVE QUERY

    //FINDING THE PRODUCT OF THE PRICE GREATER THAN THE PRICE PROVIDED.
    @Query(value = "select * from products where price >= :price", nativeQuery = true)
    public List<Product> getProductGreaterThanPrice(@Param("price") double price);


    //FINDING THE PRODUCTS MANUFACTURED BY A CERTAIN MANUFACTURER
    @Query(value = "select * from products where product_manufacturer = :manufacturer", nativeQuery = true)
    public List<Product> getProductOfManufacturer(@Param("manufacturer") String manufacturer);

    //FINDING PRODUCTS BETWEEN TWO PRICES:
    @Query(value = "select * from products where price between :minPrice and :maxPrice", nativeQuery = true)
    public List<Product> getProductBetweenPrice(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
