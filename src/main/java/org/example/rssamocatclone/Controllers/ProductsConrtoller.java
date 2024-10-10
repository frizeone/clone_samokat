package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.ProductsService;
import org.example.rssamocatclone.dto.ProductsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsConrtoller {
    private final ProductsService productsService;

    public ProductsConrtoller(ProductsService productsService) {
        this.productsService = productsService;
    }


    @PostMapping
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO productsDTO) {
        ProductsDTO createdProduct = productsService.createProduct(productsDTO);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable int id) {
        ProductsDTO product = productsService.getProductById(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAllProducts() {
        List<ProductsDTO> products = productsService.getAllProducts();
        return ResponseEntity.ok(products);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProduct(@PathVariable int id, @RequestBody ProductsDTO productsDTO) {
        ProductsDTO updatedProduct = productsService.updateProduct(id, productsDTO);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
