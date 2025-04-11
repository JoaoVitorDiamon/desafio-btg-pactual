package com.techBTG.testBTG.Controller.DTO;

import com.techBTG.testBTG.Entity.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        Long customersId,
        BigDecimal total) {


    public static OrderResponse fromEntity(OrderEntity orderEntity) {
        return  new OrderResponse(
                orderEntity.getOrderId(),
                orderEntity.getCustomerID(),
                orderEntity.getTotal());
    }
}