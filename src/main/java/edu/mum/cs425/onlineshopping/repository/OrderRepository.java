package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs425.onlineshopping.entity.OrderMain;


public interface OrderRepository extends JpaRepository<OrderMain, Integer>{
    OrderMain findByOrderId(Long orderId);


    Page<OrderMain> findAllByOrderStatusOrderByCreateTimeAsc(Integer orderStatus, Pageable pageable);


    Page<OrderMain> findAllByBuyerEmailOrderByOrderStatusAscCreateTimeAsc(String buyerEmail, Pageable pageable);

    Page<OrderMain> findAllByOrderByOrderStatusAscCreateTimeAsc( Pageable pageable);

    Page<OrderMain> findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeAsc(String buyerPhone, Pageable pageable);
}
