package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.ProductsService;
import org.example.rssamocatclone.dto.ProductsDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsConrtoller {
    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final ProductsService productsService;

    public ProductsConrtoller(RabbitTemplate rabbitTemplate, ProductsService productsService) {
        this.rabbitTemplate = rabbitTemplate;
        this.productsService = productsService;
    }


    @PostMapping
    public ResponseEntity<ProductsDTO> createProduct(@RequestBody ProductsDTO productsDTO) {
        ProductsDTO createdProduct = productsService.createProduct(productsDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key" ,"[ProductsConrtoller] [Сработал метод createProduct] Создан продукт = " + createdProduct.logMethod());
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsDTO> getProductById(@PathVariable int id) {
        ProductsDTO product = productsService.getProductById(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key" ,"[ProductsConrtoller] [Сработал метод getProductById] " + product.logMethod());
        return ResponseEntity.ok(product);
    }


    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getAllProducts() {
        List<ProductsDTO> products = productsService.getAllProducts();
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key" ,"[ProductsConrtoller] [Сработал метод getAllProducts] [START]");
        for (ProductsDTO product : products) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key",product.logMethod());
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key","[ProductsConrtoller] [Сработал метод getAllProducts] [END]");
        return ResponseEntity.ok(products);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductsDTO> updateProduct(@PathVariable int id, @RequestBody ProductsDTO productsDTO) {
        ProductsDTO updatedProduct = productsService.updateProduct(id, productsDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key","[ProductsConrtoller] [Сработал метод updateProduct] [Обновлен пользователь с id = {" + id + "} ] " + updatedProduct.logMethod());
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productsService.deleteProduct(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key","[ProductsConrtoller] [Сработал метод deleteProduct] [Удален пользователь с id = {" + id + "}]");
        return ResponseEntity.noContent().build();
    }
}
