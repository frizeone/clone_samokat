package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.OrdersService;
import org.example.rssamocatclone.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }


    @GetMapping
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable int id) {
        OrdersDTO orderDTO = ordersService.getOrderById(id);
        if (orderDTO != null) {
            return ResponseEntity.ok(orderDTO);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<OrdersDTO> createOrder(@RequestBody OrdersDTO ordersDTO) {
        OrdersDTO createdOrder = ordersService.createOrder(ordersDTO);
        return ResponseEntity.ok(createdOrder);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrder(@PathVariable int id, @RequestBody OrdersDTO ordersDTO) {
        OrdersDTO updatedOrder = ordersService.updateOrder(id, ordersDTO);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
