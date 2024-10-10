package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.ProductsDTO;

import java.util.List;

public interface ProductsService {
    ProductsDTO createProduct(ProductsDTO productsDTO);

    ProductsDTO getProductById(int id);

    List<ProductsDTO> getAllProducts();

    ProductsDTO updateProduct(int id, ProductsDTO productsDTO);

    void deleteProduct(int id);
}
