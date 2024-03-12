package com.example.ecommerce.controller;


import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    //VARIABLES
    private ProductService productService;

    //CONSTRUCTOR
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //
    //
    //
    //ADD PRODUCT REST API
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    //
    //
    //
    //GET A PRODUCT  - REST API
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAProduct(@PathVariable(name = "id") Long id){

        try{
            Product product = productService.getAProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //
    //
    //
    //GET ALL PRODUCTS REST API
    @GetMapping()
    public ResponseEntity<?> getAllProducts(){
        try{

            List<Product> product = productService.getProducts();
            return new ResponseEntity<>(product, HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //
    //
    //
    //UPDATE A PRODUCT REST API
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAProduct(@PathVariable(name = "id") Long id,
                                                     @RequestBody Map<String, String> body){

        try{
            //Creating new object of productDto which to be provided to the service layer
            Product product = new Product(
                    id,
                    body.get("productName"),
                    Long.parseLong(body.get("quantity")),
                    body.get("productDescription"),
                    Double.parseDouble(body.get("price")),
                    body.get("productManufacturer")
            );
            //Updating the existing product in the database
            Product updatedProduct = productService.updateAProduct(id,product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //
    //
    //
    //DELETE A PRODUCT - REST API
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteAProduct(@PathVariable(name = "id") Long id){
        try{
            productService.deleteAProduct(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //
    //CUSTOM QUERIES RESTFUL APIs
    //


    //
    //
    //
    //PRODUCT WITH PRICE GREATER THAN - REST API
    @GetMapping(path = "/greaterThanPrice/{price}")
    public ResponseEntity<?> greaterThanPrice(@PathVariable(name = "price") double price){

        try {
            List<Product> products = productService.getProductGreaterThanPrice(price);
            if(!products.isEmpty()){
                return new ResponseEntity<>(products, HttpStatus.FOUND);
            }
            return ResponseEntity.ok().body("No Products Present for Price greater or equal to " + price);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //
    //
    //
    //----------USING QUERY PARAM-------------
    //PRODUCT WITH PRICE BETWEEN RANGE - REST API
@GetMapping(path = "/priceBetween")
public ResponseEntity<?> getProductsBetweenPrice(
        @RequestParam double minPrice,
        @RequestParam double maxPrice
){
        try {
            List<Product> products = productService.getProductsBetweenPrice(minPrice, maxPrice);
            if(!products.isEmpty()){
                return new ResponseEntity<>(products, HttpStatus.FOUND);
            }
            return ResponseEntity.ok().body("There is not product between " + minPrice + " and " + maxPrice);
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }

}

    //
    //
    //
    //Getting all manufacturer
    @GetMapping(path = "/manufacturer/{manufacturer}")
    public ResponseEntity<?> productOfManufacturer(@PathVariable(name = "manufacturer") String manufacturer){

        try{
            List<Product> products = productService.getProductOfManufacturer(manufacturer);
            if(!products.isEmpty()){
                return new ResponseEntity<>(products, HttpStatus.OK);
            }

            return ResponseEntity.ok().body("No Products from the " + manufacturer);
        }catch (Exception e){
            return ResponseEntity.ok().body(e.getMessage());
        }


    }


    //-----END BRACKET------
}
