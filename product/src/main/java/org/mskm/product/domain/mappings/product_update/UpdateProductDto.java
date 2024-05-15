package org.mskm.product.domain.mappings.product_update;

import org.mskm.product.domain.Product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
public record UpdateProductDto(String name, double value) implements Serializable {
}