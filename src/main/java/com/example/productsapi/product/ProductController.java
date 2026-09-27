package com.example.productsapi.product;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @Valid @RequestBody ProductRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        Product product = productRepository.save(new Product(request.name(), request.price()));
        return ResponseEntity.created(
                uriComponentsBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri())
                .body(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
