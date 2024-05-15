package org.mskm.product.domain;

import lombok.AllArgsConstructor;
import org.mskm.product.domain.mappings.product.ProductDto;
import org.mskm.product.domain.mappings.product_create.CreateProductDto;
import org.mskm.product.domain.mappings.product_update.UpdateProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductResource {
    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDto> createProduct(@RequestBody CreateProductDto product) {
        return productService.create(product);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable("id") String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable("id") String id,
                                                          @RequestBody UpdateProductDto product) {
        return productService.update(id, product)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable("id") String id) {
        return productService.delete(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllProduct() {
        return productService.deleteAll();
    }

}
