package org.example.rssamocatclone.repository;

import org.example.rssamocatclone.dto.OrderItemsDTO;
import org.example.rssamocatclone.models.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Integer> {

    @Query("SELECT  oi from OrderItems oi where oi.order.id = :orderId")
    List<OrderItems> findOrderItemsByOrderId(@Param("orderId") int orderId);
}

