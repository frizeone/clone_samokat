package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.OrderItemsService;
import org.example.rssamocatclone.dto.OrderItemsDTO;
import org.example.rssamocatclone.repository.OrderItemRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderItemsController {

    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;


    private final OrderItemsService orderItemsService;

    public OrderItemsController(RabbitTemplate rabbitTemplate, OrderItemsService orderItemsService) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderItemsService = orderItemsService;
    }


    @GetMapping("/{orderId}/items")
    public List<OrderItemsDTO> getOrderItems(@PathVariable int orderId) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderItemsController][Сработал метод getOrderItems]");
        return orderItemsService.findOrderItemsByOrderId(orderId);
    }
}
