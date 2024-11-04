package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.OrdersDTO;
import org.example.rssamocatclone.models.OrderStatus;
import org.example.rssamocatclone.models.Orders;

import java.time.LocalDateTime;
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

        // Проверяем статус и устанавливаем его в заказ
        if (dto.getStatus() != null) {
            OrderStatus status = new OrderStatus();
            status.setStatusName(dto.getStatus()); // Устанавливаем статус из DTO
            order.setStatus(status); // Устанавливаем объект статуса в заказ
        }

        order.setCreatedAt(LocalDateTime.now()); // Установка текущей даты
        // Инициализация других полей (например, связанных объектов)
        return order;
    }


}
