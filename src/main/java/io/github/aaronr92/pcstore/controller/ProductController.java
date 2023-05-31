package io.github.aaronr92.pcstore.controller;

import io.github.aaronr92.pcstore.dto.ProductDTO;
import io.github.aaronr92.pcstore.dto.PropertyDTO;
import io.github.aaronr92.pcstore.dto.SavedProductDTO;
import io.github.aaronr92.pcstore.entity.Product;
import io.github.aaronr92.pcstore.repository.ProductRepository;
import io.github.aaronr92.pcstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<SavedProductDTO> addProduct(
            @RequestBody ProductDTO productDTO
    ) {
        var product = productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("{id}")
    public void updateProduct(
            @PathVariable long id,
            @RequestBody ProductDTO productDTO
    ) {
        productService.update(id, productDTO);
    }

    @GetMapping
    public ResponseEntity<List<SavedProductDTO>> getAllProductsByCategory(
            @RequestParam long categoryId
    ) {
        return ResponseEntity.ok(productService.getAllByCategory(categoryId));
    }

    @GetMapping("{id}")
    public ResponseEntity<SavedProductDTO> getProductById(
            @PathVariable long id
    ) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

}
