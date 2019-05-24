package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs425.onlineshopping.entity.ProductInfo;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String>{
    // One product
    ProductInfo findFirstByProductId(String id);
    // onsale product
    Page<ProductInfo> findAllByProductStatusOrderByProductIdAsc(Integer productStatus, Pageable pageable);

    // product in one category
    Page<ProductInfo> findAllByCategoryTypeOrderByProductIdAsc(Integer categoryType, Pageable pageable);

    Page<ProductInfo> findAllByOrderByProductId( Pageable pageable);

}
