package com.example.scalestore.service;

import com.example.scalestore.dto.order.CreateOrderRequest;
import com.example.scalestore.dto.order.OrderItemRequest;
import com.example.scalestore.dto.order.OrderResponse;
import com.example.scalestore.exception.InsufficientStockException;
import com.example.scalestore.exception.ResourceNotFoundException;
import com.example.scalestore.mapper.OrderMapper;
import com.example.scalestore.model.Order;
import com.example.scalestore.model.OrderItem;
import com.example.scalestore.model.OrderStatus;
import com.example.scalestore.model.Product;
import com.example.scalestore.model.User;
import com.example.scalestore.repository.OrderItemRepository;
import com.example.scalestore.repository.OrderRepository;
import com.example.scalestore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {

        User user = userService.getCurrentUser();

        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .user(user)
                .totalAmount(BigDecimal.ZERO)
                .build();

        order = orderRepository.save(order);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository
                    .findByIdWithLock(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found : " + itemRequest.getProductId()));

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new InsufficientStockException(
                        "Only "
                                + product.getStock()
                                + " item(s) left for "
                                + product.getName());
            }

            product.setStock(
                    product.getStock() - itemRequest.getQuantity());

            productRepository.save(product);

            BigDecimal subtotal =
                    product.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(
                                            itemRequest.getQuantity()));

            totalAmount = totalAmount.add(subtotal);

            OrderItem orderItem = createOrderItem(
                    order,
                    product,
                    itemRequest.getQuantity(),
                    subtotal);

            orderItemRepository.save(orderItem);

            order.getOrderItems().add(orderItem);
        }

        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        return orderMapper.toResponse(order);
    }
    private OrderItem createOrderItem(
            Order order,
            Product product,
            Integer quantity,
            BigDecimal subtotal) {

        return OrderItem.builder()
                .order(order)
                .product(product)
                .quantity(quantity)
                .price(product.getPrice())
                .subtotal(subtotal)
                .build();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"));

        return orderMapper.toResponse(order);
    }

}