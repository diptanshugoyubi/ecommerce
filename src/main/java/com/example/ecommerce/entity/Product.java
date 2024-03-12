package com.example.ecommerce.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@Entity(name = "products")
public class Product {

    @Id  //making id as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //auto-increment the primary key
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private Long quantity;

    @Column(name = "product_description")
    private String productDescription;

    private Double price;

    @Column(name = "product_manufacturer")
    private String productManufacturer;
}
