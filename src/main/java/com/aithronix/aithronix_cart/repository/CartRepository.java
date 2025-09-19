package com.aithronix.aithronix_cart.repository;

import com.aithronix.aithronix_cart.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CartRepository {

    private final RedisTemplate<String, Cart> redisTemplate;
    private static final long CART_TTL = 1;

    private String key(Long userId) {
        return "cart:" + userId;
    }

    public Cart findByUserId(Long userId) {
        return redisTemplate.opsForValue().get(key(userId));
    }

    public void save(Cart cart) {
        redisTemplate.opsForValue().set(key(cart.getUserId()), cart, CART_TTL, TimeUnit.DAYS);
    }

    public void delete(Long userId) {
        redisTemplate.delete(key(userId));
    }
}
