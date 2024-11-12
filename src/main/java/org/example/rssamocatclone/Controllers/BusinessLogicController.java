package org.example.rssamocatclone.Controllers;

import io.swagger.v3.oas.annotations.links.Link;
import org.example.rssamocatclone.Services.OrdersService;
import org.example.rssamocatclone.dto.OrdersDTO;
import org.example.rssamocatclone.models.Orders;
import org.example.rssamocatclone.repository.OrdersRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/business")
public class BusinessLogicController {

    static final String EXCHANGE_NAME = "testExchange";

    private final RabbitTemplate rabbitTemplate;

    private final OrdersRepository ordersRepository;

    private final OrdersService ordersService;

    public BusinessLogicController(RabbitTemplate rabbitTemplate, OrdersRepository ordersRepository, OrdersService ordersService) {
        this.rabbitTemplate = rabbitTemplate;
        this.ordersRepository = ordersRepository;
        this.ordersService = ordersService;
    }

    @GetMapping("/user-orders/{userId}")
    public CollectionModel<EntityModel<OrdersDTO>> getUserOrders(@PathVariable int userId) {

        List<OrdersDTO> orders = ordersService.findOrdersByUserId(userId);

        List<EntityModel<OrdersDTO>> orderResources = orders.stream()
                .map(order -> EntityModel.of(order,
                        linkTo(methodOn(BusinessLogicController.class).getUserOrders(userId)).withSelfRel(),
                        linkTo(methodOn(OrderItemsController.class).getOrderItems(order.getId())).withRel("order-items")
                ))
                .collect(Collectors.toList());

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "first.key", "[BusinessLogicController][Сработал метод getUserOrders]");
        return CollectionModel.of(orderResources,
                linkTo(methodOn(BusinessLogicController.class).getUserOrders(userId)).withSelfRel());
    }
}
