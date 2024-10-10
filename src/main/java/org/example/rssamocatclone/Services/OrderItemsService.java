package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.OrderItemsDTO;

import java.util.List;

public interface OrderItemsService {

    OrderItemsDTO createOrderItem(OrderItemsDTO dto);

    List<OrderItemsDTO> getAllOrderItems();

    OrderItemsDTO getOrderItemById(int id);

    OrderItemsDTO updateOrderItem(int id, OrderItemsDTO dto);

    void deleteOrderItem(int id);
}
