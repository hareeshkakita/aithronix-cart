package com.aithronix.aithronix_cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable {

    private UUID productId;
    private int quantity;
    private double price;

}
