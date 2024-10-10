package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.OrderStatusMapper;
import org.example.rssamocatclone.Services.OrderStatusService;
import org.example.rssamocatclone.dto.OrderStatusDTO;
import org.example.rssamocatclone.models.OrderStatus;
import org.example.rssamocatclone.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderStatusImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusImpl(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public List<OrderStatusDTO> getAllOrderStatuses() {
        List<OrderStatus> orderStatuses = orderStatusRepository.findAll();
        return orderStatuses.stream()
                .map(OrderStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderStatusDTO getOrderStatusById(int id) {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
        return orderStatus.map(OrderStatusMapper::toDTO).orElse(null);
    }

    public OrderStatusDTO createOrderStatus(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = OrderStatusMapper.toEntity(orderStatusDTO);
        OrderStatus savedOrderStatus = orderStatusRepository.save(orderStatus);
        return OrderStatusMapper.toDTO(savedOrderStatus);
    }

    public OrderStatusDTO updateOrderStatus(int id, OrderStatusDTO orderStatusDTO) {
        Optional<OrderStatus> optionalOrderStatus = orderStatusRepository.findById(id);
        if (optionalOrderStatus.isPresent()) {
            OrderStatus orderStatus = optionalOrderStatus.get();
            orderStatus.setStatusName(orderStatusDTO.getStatusName());
            orderStatus.setDescription(orderStatusDTO.getDescription());
            OrderStatus updatedOrderStatus = orderStatusRepository.save(orderStatus);
            return OrderStatusMapper.toDTO(updatedOrderStatus);
        }
        return null;
    }

    public boolean deleteOrderStatus(int id) {
        if (orderStatusRepository.existsById(id)) {
            orderStatusRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
