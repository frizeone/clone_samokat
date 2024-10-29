package org.example.rssamocatclone.Services.ServicesImpl;

import org.example.rssamocatclone.DTOMapper.OrderItemsMapper;
import org.example.rssamocatclone.Services.OrderItemsService;
import org.example.rssamocatclone.dto.OrderItemsDTO;
import org.example.rssamocatclone.models.OrderItems;
import org.example.rssamocatclone.repository.OrderItemRepository;
import org.example.rssamocatclone.repository.OrdersRepository;
import org.example.rssamocatclone.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    private final OrderItemRepository orderItemsRepository;

    private final OrdersRepository ordersRepository;

    private final ProductsRepository productsRepository;

    public OrderItemsServiceImpl(OrderItemRepository orderItemsRepository, OrdersRepository ordersRepository, ProductsRepository productsRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.ordersRepository = ordersRepository;
        this.productsRepository = productsRepository;
    }

    // Create
    public OrderItemsDTO createOrderItem(OrderItemsDTO dto) {
        OrderItems orderItem = OrderItemsMapper.toEntity(dto);
        orderItem.setOrder(ordersRepository.findById(dto.getOrderId()).orElseThrow());
        orderItem.setProduct(productsRepository.findById(dto.getProductId()).orElseThrow());
        return OrderItemsMapper.toDTO(orderItemsRepository.save(orderItem));
    }

    // Read all
    public List<OrderItemsDTO> getAllOrderItems() {
        return orderItemsRepository.findAll().stream()
                .map(OrderItemsMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Read by ID
    public OrderItemsDTO getOrderItemById(int id) {
        OrderItems orderItem = orderItemsRepository.findById(id).orElseThrow();
        return OrderItemsMapper.toDTO(orderItem);
    }

    // Update
    public OrderItemsDTO updateOrderItem(int id, OrderItemsDTO dto) {
        OrderItems orderItem = orderItemsRepository.findById(id).orElseThrow();
        orderItem.setQuantity(dto.getQuantity());
        orderItem.setPricePerUnit(dto.getPricePerUnit());
        orderItem.setOrder(ordersRepository.findById(dto.getOrderId()).orElseThrow());
        orderItem.setProduct(productsRepository.findById(dto.getProductId()).orElseThrow());
        return OrderItemsMapper.toDTO(orderItemsRepository.save(orderItem));
    }

    // Delete
    public void deleteOrderItem(int id) {
        orderItemsRepository.deleteById(id);
    }

    public List<OrderItemsDTO> findOrderItemsByOrderId(int id){
        List<OrderItems> orderItem = orderItemsRepository.findOrderItemsByOrderId(id);
        List<OrderItemsDTO> result = orderItem.stream().map(OrderItemsMapper::toDTO).collect(Collectors.toList());
        return result;
    }
}
