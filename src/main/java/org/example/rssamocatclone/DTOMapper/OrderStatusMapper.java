package org.example.rssamocatclone.DTOMapper;

import org.example.rssamocatclone.dto.OrderStatusDTO;
import org.example.rssamocatclone.models.OrderStatus;

public class OrderStatusMapper {

        public static OrderStatusDTO toDTO(OrderStatus status) {
            OrderStatusDTO dto = new OrderStatusDTO();
            dto.setId(status.getId());
            dto.setStatusName(status.getStatusName());
            dto.setDescription(status.getDescription());
            return dto;
        }

        public static OrderStatus toEntity(OrderStatusDTO dto) {
            OrderStatus status = new OrderStatus();
            status.setStatusName(dto.getStatusName());
            status.setDescription(dto.getDescription());
            return status;
        }
}
