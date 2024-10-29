package org.example.rssamocatclone.GraphqlClass;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class OrderInput {
    private int userId;
    private Double totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItemInput> orderItems;


}

