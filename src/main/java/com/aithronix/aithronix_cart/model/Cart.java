package com.aithronix.aithronix_cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private UUID userId;
    private List<CartItem> items = new ArrayList<>();
    private Instant updatedAt = Instant.now();
}
