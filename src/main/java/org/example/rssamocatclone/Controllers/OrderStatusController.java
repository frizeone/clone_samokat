package org.example.rssamocatclone.Controllers;


import org.example.rssamocatclone.Services.OrderStatusService;
import org.example.rssamocatclone.dto.OrderStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-status")
public class OrderStatusController {

    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @GetMapping
    public List<OrderStatusDTO> getAllOrderStatuses() {
        return orderStatusService.getAllOrderStatuses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> getOrderStatusById(@PathVariable int id) {
        OrderStatusDTO orderStatusDTO = orderStatusService.getOrderStatusById(id);
        if (orderStatusDTO != null) {
            return ResponseEntity.ok(orderStatusDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<OrderStatusDTO> createOrderStatus(@RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatusDTO createdOrderStatus = orderStatusService.createOrderStatus(orderStatusDTO);
        return ResponseEntity.ok(createdOrderStatus);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderStatusDTO> updateOrderStatus(@PathVariable int id, @RequestBody OrderStatusDTO orderStatusDTO) {
        OrderStatusDTO updatedOrderStatus = orderStatusService.updateOrderStatus(id, orderStatusDTO);
        if (updatedOrderStatus != null) {
            return ResponseEntity.ok(updatedOrderStatus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable int id) {
        boolean isDeleted = orderStatusService.deleteOrderStatus(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
