package org.example.rssamocatclone.Services;

import org.example.rssamocatclone.dto.OrderStatusDTO;

import java.util.List;

public interface OrderStatusService {

    List<OrderStatusDTO> getAllOrderStatuses();

    OrderStatusDTO getOrderStatusById(int id);

    OrderStatusDTO createOrderStatus(OrderStatusDTO orderStatusDTO);

    OrderStatusDTO updateOrderStatus(int id, OrderStatusDTO orderStatusDTO);

    boolean deleteOrderStatus(int id);
}
