package edu.mum.cs425.onlineshopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs425.onlineshopping.entity.OrderMain;
import edu.mum.cs425.onlineshopping.entity.ProductInOrder;
import edu.mum.cs425.onlineshopping.entity.ProductInfo;
import edu.mum.cs425.onlineshopping.enums.OrderStatusEnum;
import edu.mum.cs425.onlineshopping.enums.ResultEnum;
import edu.mum.cs425.onlineshopping.exception.MyException;
import edu.mum.cs425.onlineshopping.repository.OrderRepository;
import edu.mum.cs425.onlineshopping.repository.ProductInOrderRepository;
import edu.mum.cs425.onlineshopping.repository.ProductInfoRepository;
import edu.mum.cs425.onlineshopping.repository.UserRepository;
import edu.mum.cs425.onlineshopping.service.OrderService;
import edu.mum.cs425.onlineshopping.service.ProductService;

import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Page<OrderMain> findAll(Pageable pageable) {
        return orderRepository.findAllByOrderByOrderStatusAscCreateTimeAsc(pageable);
    }

    @Override
    public Page<OrderMain> findByStatus(Integer status, Pageable pageable) {
        return orderRepository.findAllByOrderStatusOrderByCreateTimeAsc(status, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerEmail(String email, Pageable pageable) {
        return orderRepository.findAllByBuyerEmailOrderByOrderStatusAscCreateTimeAsc(email, pageable);
    }

    @Override
    public Page<OrderMain> findByBuyerPhone(String phone, Pageable pageable) {
        return orderRepository.findAllByBuyerPhoneOrderByOrderStatusAscCreateTimeAsc(phone, pageable);
    }

    @Override
    public OrderMain findOne(Long orderId) {
        OrderMain orderMain = orderRepository.findByOrderId(orderId);
        if(orderMain == null) {
            throw new MyException(ResultEnum.ORDER_NOT_FOUND);
        }
        return orderMain;
    }

    @Override
    @Transactional
    public void finish(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderRepository.save(orderMain);
    }

    @Override
    @Transactional
    public void cancel(Long orderId) {
        OrderMain orderMain = findOne(orderId);
        if(!orderMain.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            throw new MyException(ResultEnum.ORDER_STATUS_ERROR);
        }

        orderMain.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        orderRepository.save(orderMain);

        // Restore Stock
        Iterable<ProductInOrder> products = orderMain.getProducts();
        for(ProductInOrder productInOrder : products) {
            ProductInfo productInfo = productInfoRepository.findFirstByProductId(productInOrder.getProductId());
            if(productInfo != null) {
                productService.increaseStock(productInOrder.getProductId(), productInOrder.getProductQuantity());
            }
        }
    }
}
