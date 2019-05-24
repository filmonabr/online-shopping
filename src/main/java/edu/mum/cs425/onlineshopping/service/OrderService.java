package edu.mum.cs425.onlineshopping.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.mum.cs425.onlineshopping.entity.OrderMain;

@Service
public interface OrderService {
    Page<OrderMain> findAll(Pageable pageable);

    Page<OrderMain> findByStatus(Integer status, Pageable pageable);

    Page<OrderMain> findByBuyerEmail(String email, Pageable pageable);

    Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable);

    OrderMain findOne(Long orderId);



    void finish(Long orderId);

    void  cancel(Long orderId);

}
