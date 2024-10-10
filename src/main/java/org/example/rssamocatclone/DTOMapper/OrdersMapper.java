package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.OrdersDTO;
import org.example.rssamocatclone.models.Orders;

import java.util.stream.Collectors;

public class OrdersMapper {
    public static OrdersDTO toDTO(Orders order) {
        OrdersDTO dto = new OrdersDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUsers().getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setStatus(order.getStatus().getStatusName());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setOrderItems(order.getOrderItems().stream()
                .map(OrderItemsMapper::toDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    public static Orders toEntity(OrdersDTO dto) {
        Orders order = new Orders();
        order.setTotalPrice(dto.getTotalPrice());
        return order;
    }
}
