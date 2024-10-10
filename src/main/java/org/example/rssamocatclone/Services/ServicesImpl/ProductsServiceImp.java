package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.ProductsMapper;
import org.example.rssamocatclone.Services.ProductsService;
import org.example.rssamocatclone.dto.ProductsDTO;
import org.example.rssamocatclone.models.Products;
import org.example.rssamocatclone.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImp implements ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsServiceImp(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    // Create a new product
    public ProductsDTO createProduct(ProductsDTO productsDTO) {
        Products product = ProductsMapper.toEntity(productsDTO);
        Products savedProduct = productsRepository.save(product);
        return ProductsMapper.toDTO(savedProduct);
    }

    // Get a product by ID
    public ProductsDTO getProductById(int id) {
        Optional<Products> productOpt = productsRepository.findById(id);
        if (productOpt.isPresent()) {
            return ProductsMapper.toDTO(productOpt.get());
        }
        throw new RuntimeException("Product not found");
    }

    // Get all products
    public List<ProductsDTO> getAllProducts() {
        List<Products> products = productsRepository.findAll();
        return products.stream().map(ProductsMapper::toDTO).collect(Collectors.toList());
    }

    // Update an existing product
    public ProductsDTO updateProduct(int id, ProductsDTO productsDTO) {
        Optional<Products> productOpt = productsRepository.findById(id);
        if (productOpt.isPresent()) {
            Products product = productOpt.get();
            product.setName(productsDTO.getName());
            product.setDescription(productsDTO.getDescription());
            product.setPrice(productsDTO.getPrice().doubleValue());
            product.setStockQuantity(productsDTO.getStockQuantity());
            Products updatedProduct = productsRepository.save(product);
            return ProductsMapper.toDTO(updatedProduct);
        }
        throw new RuntimeException("Product not found");
    }

    // Delete a product by ID
    public void deleteProduct(int id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
}
