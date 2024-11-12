package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.OrdersService;
import org.example.rssamocatclone.dto.OrdersDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final OrdersService ordersService;

    public OrdersController(RabbitTemplate rabbitTemplate, OrdersService ordersService) {
        this.rabbitTemplate = rabbitTemplate;
        this.ordersService = ordersService;
    }


    @GetMapping
    public List<OrdersDTO> getAllOrders() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод getAllOrders]");
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable int id) {
        OrdersDTO orderDTO = ordersService.getOrderById(id);
        if (orderDTO != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод getOrderById][+]" + orderDTO.logMethod());
            return ResponseEntity.ok(orderDTO);
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод getOrderById][-]" + orderDTO.logMethod());
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO createdOrder = ordersService.createOrder(ordersDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод createOrder]" + createdOrder.logMethod());
        return ResponseEntity.ok(createdOrder);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrder(@PathVariable int id, @RequestBody OrdersDTO ordersDTO) {
        OrdersDTO updatedOrder = ordersService.updateOrder(id, ordersDTO);
        if (updatedOrder != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод updateOrder][+]" + updatedOrder.logMethod());
            return ResponseEntity.ok(updatedOrder);
        }
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод updateOrder][-]" + updatedOrder.logMethod());
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        ordersService.deleteOrder(id);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrdersController][Сработал метод updateOrder][Удален пользователь с id = {" + id + "}]" );
        return ResponseEntity.noContent().build();
    }
}
