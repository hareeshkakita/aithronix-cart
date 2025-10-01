package com.aithronix.aithronix_cart.mapper;

import com.aithronix.aithronix_cart.model.CartItem;
import com.aithronix.aithronix_order.dto.OrderItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", expression = "java(java.math.BigDecimal.valueOf(cartItem.getPrice()))")
    OrderItemRequest cartItemToOrderItemRequest(CartItem cartItem);
}
