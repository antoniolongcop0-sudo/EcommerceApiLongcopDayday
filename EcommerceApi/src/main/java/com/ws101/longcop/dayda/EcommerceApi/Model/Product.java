package com.ws101.longcop.dayda.EcommerceApi.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * Represents an e-commerce product domain entity.
 * @author Antonio Longcop Jr. N.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String category;
    private int stockQuantity;
    private String imageUrl;
}