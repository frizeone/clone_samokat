package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.OrdersMapper;
import org.example.rssamocatclone.Services.OrdersService;
import org.example.rssamocatclone.dto.OrdersDTO;
import org.example.rssamocatclone.models.Orders;
import org.example.rssamocatclone.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    // Получить все заказы
    public List<OrdersDTO> getAllOrders() {
        return ordersRepository.findAll().stream()
                .map(OrdersMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Получить заказ по ID
    public OrdersDTO getOrderById(int id) {
        Optional<Orders> order = ordersRepository.findById(id);
        return order.map(OrdersMapper::toDTO).orElse(null);
    }

    // Создать новый заказ
    public OrdersDTO createOrder(OrdersDTO ordersDTO) {
        Orders order = OrdersMapper.toEntity(ordersDTO);
        Orders savedOrder = ordersRepository.save(order);
        return OrdersMapper.toDTO(savedOrder);
    }

    // Обновить заказ
    public OrdersDTO updateOrder(int id, OrdersDTO ordersDTO) {
        Optional<Orders> existingOrder = ordersRepository.findById(id);
        if (existingOrder.isPresent()) {
            Orders order = existingOrder.get();
            order.setTotalPrice(ordersDTO.getTotalPrice());
            order.setUpdatedAt(ordersDTO.getCreatedAt());
            Orders updatedOrder = ordersRepository.save(order);
            return OrdersMapper.toDTO(updatedOrder);
        }
        return null; // Либо выбрасывать исключение
    }

    // Удалить заказ
    public void deleteOrder(int id) {
        ordersRepository.deleteById(id);
    }


    @Override
    public List<OrdersDTO> findOrdersByUserId(int id) {
        List<Orders> orders = ordersRepository.findOrdersByUserId(id);
        List<OrdersDTO> result = orders.stream().map(OrdersMapper::toDTO).collect(Collectors.toList());
        return result;
    }
}
