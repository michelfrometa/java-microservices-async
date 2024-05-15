package org.mskm.product.domain.mappings.product_create;

import org.mskm.product.domain.Product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
public record CreateProductDto(String name, double value) implements Serializable {
}