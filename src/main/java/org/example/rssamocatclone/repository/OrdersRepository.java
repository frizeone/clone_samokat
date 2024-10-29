package org.example.rssamocatclone.repository;

import jakarta.persistence.criteria.Order;
import org.example.rssamocatclone.dto.OrdersDTO;
import org.example.rssamocatclone.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {


    @Query("SELECT o FROM Orders o WHERE o.users.id = :userId")
    List<Orders> findOrdersByUserId(@Param("userId") int userId);
}
