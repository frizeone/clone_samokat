package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.OrdersDTO;

import java.util.List;

public interface OrdersService {
    List<OrdersDTO> getAllOrders();

    OrdersDTO getOrderById(int id);

    OrdersDTO createOrder(OrdersDTO ordersDTO);

    OrdersDTO updateOrder(int id, OrdersDTO ordersDTO);

    void deleteOrder(int id);

    List<OrdersDTO> findOrdersByUserId(int id);
}
