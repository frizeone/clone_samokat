package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.Services.OrderItemsService;
import org.example.rssamocatclone.dto.OrderItemsDTO;
import org.example.rssamocatclone.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderItemsController {



    private final OrderItemsService orderItemsService;

    public OrderItemsController( OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }


    @GetMapping("/{orderId}/items")
    public List<OrderItemsDTO> getOrderItems(@PathVariable int orderId) {
        return orderItemsService.findOrderItemsByOrderId(orderId);
    }
}
