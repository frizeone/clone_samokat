package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.OrderItemsDTO;
import org.example.rssamocatclone.models.OrderItems;

public class OrderItemsMapper {
    public static OrderItemsDTO toDTO(OrderItems orderItem) {
        OrderItemsDTO dto = new OrderItemsDTO();
        dto.setId(orderItem.getId());
        dto.setProductId(orderItem.getProduct().getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPricePerUnit(orderItem.getPricePerUnit());
        return dto;
    }

    public static OrderItems toEntity(OrderItemsDTO dto) {
        OrderItems orderItem = new OrderItems();
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPricePerUnit(dto.getPricePerUnit());
        return orderItem;
    }
}
