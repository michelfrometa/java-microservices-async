package org.mskm.product;

import org.junit.jupiter.api.Test;
import org.mskm.product.configuration.TestContainersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {

    public static void main(String[] args) {
        SpringApplication.from(ProductApplication::main)
                .with(TestContainersConfiguration.class)
                .run(args);
    }

    @Test
    void contextLoads() {
    }

}
