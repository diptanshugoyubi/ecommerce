package com.example.ecommerce.controller;


import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    //ADD PRODUCT REST API
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    //GET A PRODUCT REST API
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAProduct(@PathVariable(name = "id") Long id){

            Product product = productService.getAProduct(id);
            System.out.println("error not implemented properly");

            if(product.getId() != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().body("No product for the Id: " + id);

    }

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


    //UPDATE A PRODUCT REST API
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAProduct(@PathVariable(name = "id") Long id,
                                                     @RequestBody Map<String, String> body){

        //Creating new object of productDto which to be provided to the service layer
        Product product = new Product(
                id,
                body.get("productName"),
                Long.parseLong(body.get("quantity")),
                body.get("productDescription"),
                Double.parseDouble(body.get("price")),
                body.get("productManufacturer")
        );
        Product updatedProduct = productService.updateAProduct(id,product);

        if(updatedProduct.getId() != null){
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("Could Able to update the data as Id " + id + " not valid" );

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteAProduct(@PathVariable(name = "id") Long id){
        try{
            productService.deleteAProduct(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }


    //CUSTOM QUERIES

    @GetMapping(path = "/greaterThanPrice/{price}")
    public ResponseEntity<?> greaterThanPrice(@PathVariable(name = "price") double price){

        List<Product> products = productService.getProductGreaterThanPrice(price);
        if(!products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return ResponseEntity.ok().body("No Products Present for Price >= " + price);

    }


    //USING QUERY PARAM___________
@GetMapping(path = "/priceBetween")
public ResponseEntity<?> getProductsBetweenPrice(
        @RequestParam double minPrice,
        @RequestParam double maxPrice
){
        List<Product> products = productService.getProductsBetweenPrice(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
}

//    @GetMapping("/products")
//    public ResponseEntity<List<ProductDetails>> getProductsByPriceRange(
//            @RequestParam double minPrice,
//            @RequestParam double maxPrice) {
//
//        System.out.println(maxPrice + ":" + minPrice);
//        // Call your service to retrieve products based on the minPrice and maxPrice parameters
//        List<ProductDetails> products = productService.getProductsByPriceRange(minPrice, maxPrice);
//
//        // Return the products in a ResponseEntity with appropriate status code
//        return ResponseEntity.ok(products);
//    }


    //Getting all manufacturer
    @GetMapping(path = "/manufacturer/{manufacturer}")
    public ResponseEntity<?> productOfManufacturer(@PathVariable(name = "manufacturer") String manufacturer){

        List<Product> products = productService.getProductOfManufacturer(manufacturer);
        if(!products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        return ResponseEntity.ok().body("No Products from the " + manufacturer);

    }


}
