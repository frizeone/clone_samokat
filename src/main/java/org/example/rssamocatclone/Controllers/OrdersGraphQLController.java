package org.example.rssamocatclone.Controllers;

import org.example.rssamocatclone.GraphqlClass.OrderInput;
import org.example.rssamocatclone.Services.OrdersService;
import org.example.rssamocatclone.dto.OrderItemsDTO;
import org.example.rssamocatclone.dto.OrdersDTO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrdersGraphQLController {

    private final OrdersService ordersService;

    public OrdersGraphQLController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // Мутация для создания нового заказа
    @MutationMapping
    public OrdersDTO createOrder(@Argument("orderInput") OrderInput orderInput) {
        // Проверка входных данных
        if (orderInput == null || orderInput.getUserId() < 0 || orderInput.getTotalPrice() < 0 || orderInput.getOrderItems() == null) {
            throw new IllegalArgumentException("Invalid order input");
        }

        // Создание нового заказа с использованием конструктора
        OrdersDTO newOrder = new OrdersDTO(orderInput.getUserId(), orderInput.getTotalPrice(), orderInput.getStatus());

        // Преобразование orderItems с использованием Stream API
        List<OrderItemsDTO> orderItemsDTOs = orderInput.getOrderItems().stream()
                .map(item -> new OrderItemsDTO(item.getProductId(), item.getQuantity(), item.getPricePerUnit()))
                .collect(Collectors.toList());

        newOrder.setOrderItems(orderItemsDTOs);

        // Логирование создания заказа (при наличии логгера)
        // logger.info("Creating new order for user: " + orderInput.getUserId());

        return ordersService.createOrder(newOrder);
    }


    // Пример запроса: получение заказа по ID
    @SchemaMapping(typeName = "Query", field = "getOrderById")
    public OrdersDTO getOrderById(int id) {
        return ordersService.getOrderById(id);
    }

    // Пример запроса: получение всех заказов
    @SchemaMapping(typeName = "Query", field = "getAllOrders")
    public List<OrdersDTO> getAllOrders() {
        return ordersService.getAllOrders();
    }
}
