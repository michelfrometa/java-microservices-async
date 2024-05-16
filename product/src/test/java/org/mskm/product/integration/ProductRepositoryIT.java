package org.mskm.product.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mskm.product.configuration.TestContainersConfiguration;
import org.mskm.product.domain.Product;
import org.mskm.product.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import(TestContainersConfiguration.class)
class ProductRepositoryIT {

    private final String productName = "Product 1";
    private final Double productValue = 100.0;
    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setup() {
        product = Product.builder()
                .name(productName)
                .value(productValue)
                .build();
    }

    @DisplayName("JUnit test for save product operation")
    @Test
    void givenProductObject_whenSave_thenReturnSavedProduct() {

        Mono<Product> savedProduct = productRepository.save(product);

        StepVerifier.create(savedProduct)
                .expectNextCount(1)
                .consumeNextWith(product -> {
                    assertThat(product).isNotNull();
                    assertThat(product.getId()).isNotNull();
                })
                .verifyComplete();
    }


    @DisplayName("JUnit test for get all products operation")
    @Test
    void givenProductsList_whenFindAll_thenProductsList() {
        // given - precondition or setup

        Product product1 = Product.builder()
                .name("Product 2")
                .value(200.0)
                .build();

        productRepository.save(product);
        productRepository.save(product1);

        // when -  action or the behaviour that we are going test
        Flux<Product> productList = productRepository.findAll();

        StepVerifier.create(productList)
                .expectNextCount(2)
                .consumeNextWith(product -> {
                    assertThat(product.getId()).isNotNull();
                    assertThat(product.getName()).isEqualTo(productName);
                    assertThat(product.getValue()).isEqualTo(productValue);
                })
                .consumeNextWith(product -> {
                    assertThat(product.getId()).isNotNull();
                    assertThat(product.getName()).isEqualTo("Product 2");
                    assertThat(product.getValue()).isEqualTo(200.0);
                })
                .verifyComplete();
    }/*

    @DisplayName("JUnit test for get product by id operation")
    @Test
    void givenProductObject_whenFindById_thenReturnProductObject() {
        // given - precondition or setup
        productRepository.save(product);

        // when -  action or the behaviour that we are going test
        Product productDB = productRepository.findById(product.getId()).get();

        // then - verify the output
        assertThat(productDB).isNotNull();
    }

    @DisplayName("JUnit test for get product by email operation")
    @Test
    void givenProductEmail_whenFindByEmail_thenReturnProductObject() {
        // given - precondition or setup
        productRepository.save(product);

        // when -  action or the behaviour that we are going test
        Product productDB = productRepository.findByEmail(product.getEmail()).get();

        // then - verify the output
        assertThat(productDB).isNotNull();
    }

    @DisplayName("JUnit test for update product operation")
    @Test
    void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct() {
        // given - precondition or setup
        productRepository.save(product);

        // when -  action or the behaviour that we are going test
        Product savedProduct = productRepository.findById(product.getId()).get();
        savedProduct.setEmail("ram@gmail.com");
        savedProduct.setFirstName("Ram");
        Product updatedProduct =  productRepository.save(savedProduct);

        // then - verify the output
        assertThat(updatedProduct.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedProduct.getFirstName()).isEqualTo("Ram");
    }

    @DisplayName("JUnit test for delete product operation")
    @Test
    void givenProductObject_whenDelete_thenRemoveProduct() {
        // given - precondition or setup
        productRepository.save(product);

        // when -  action or the behaviour that we are going test
        productRepository.deleteById(product.getId());
        Optional<Product> productOptional = productRepository.findById(product.getId());

        // then - verify the output
        assertThat(productOptional).isEmpty();
    }

    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    void givenFirstNameAndLastName_whenFindByJPQL_thenReturnProductObject() {
        // given - precondition or setup
        productRepository.save(product);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        Product savedProduct = productRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

    @DisplayName("JUnit test for custom query using JPQL with Named params")
    @Test
    void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnProductObject() {
        // given - precondition or setup
        productRepository.save(product);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        Product savedProduct = productRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnProductObject() {
        // given - precondition or setup
        productRepository.save(product);
        // String firstName = "Ramesh";
        // String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        Product savedProduct = productRepository.findByNativeSQL(product.getFirstName(), product.getLastName());

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }

    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnProductObject() {
        // given - precondition or setup
        productRepository.save(product);
        // String firstName = "Ramesh";
        // String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        Product savedProduct = productRepository.findByNativeSQLNamed(product.getFirstName(), product.getLastName());

        // then - verify the output
        assertThat(savedProduct).isNotNull();
    }*/

}
