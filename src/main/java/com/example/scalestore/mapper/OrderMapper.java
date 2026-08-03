package com.example.scalestore.mapper;

import com.example.scalestore.dto.order.OrderItemResponse;
import com.example.scalestore.dto.order.OrderResponse;
import com.example.scalestore.model.Order;
import com.example.scalestore.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponse toResponse(Order order) {

        List<OrderItemResponse> items = order.getOrderItems()
                .stream()
                .map(this::toItemResponse)
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus(),
                items
        );
    }

    private OrderItemResponse toItemResponse(OrderItem item) {

        return new OrderItemResponse(
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getQuantity(),
                item.getPrice(),
                item.getSubtotal()
        );
    }
}