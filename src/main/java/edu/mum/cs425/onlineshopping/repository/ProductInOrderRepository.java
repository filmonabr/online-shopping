package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs425.onlineshopping.entity.ProductInOrder;


public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

}
