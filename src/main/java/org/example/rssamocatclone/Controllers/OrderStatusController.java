package org.example.rssamocatclone.Controllers;


import org.example.rssamocatclone.Services.OrderStatusService;
import org.example.rssamocatclone.dto.OrderStatusDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-status")
public class OrderStatusController {

    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final OrderStatusService orderStatusService;

    public OrderStatusController(RabbitTemplate rabbitTemplate, OrderStatusService orderStatusService) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public List<OrderStatusDTO> getAllOrderStatuses() {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод getAllOrderStatuses]");
        return orderStatusService.getAllOrderStatuses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatusById(@PathVariable int id) {
        OrderStatusDTO orderStatusDTO = orderStatusService.getOrderStatusById(id);
        if (orderStatusDTO != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод getOrderStatusById][+]" + orderStatusDTO.logMethod());
            return ResponseEntity.ok(orderStatusDTO);
        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод getOrderStatusById][-]" + orderStatusDTO.logMethod());
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<OrderStatusDTO> createOrderStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatusDTO createdOrderStatus = orderStatusService.createOrderStatus(orderStatusDTO);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод createOrderStatus]" + orderStatusDTO.logMethod());
        return ResponseEntity.ok(createdOrderStatus);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatusDTO updatedOrderStatus = orderStatusService.updateOrderStatus(id, orderStatusDTO);
        if (updatedOrderStatus != null) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод updateOrderStatus][+]" + orderStatusDTO.logMethod());
            return ResponseEntity.ok(updatedOrderStatus);
        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод updateOrderStatus][-]" + orderStatusDTO.logMethod());
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable int id) {
        boolean isDeleted = orderStatusService.deleteOrderStatus(id);
        if (isDeleted) {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод deleteOrderStatus][+]" + "id = {" + id + "}");
            return ResponseEntity.ok().build();
        } else {
            rabbitTemplate.convertAndSend(EXCHANGE_NAME,"first.key", "[OrderStatusController][Сработал метод deleteOrderStatus][-]" + "id = {" + id + "}");
            return ResponseEntity.notFound().build();
        }
    }

}
