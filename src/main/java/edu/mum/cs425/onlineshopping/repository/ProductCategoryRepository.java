package edu.mum.cs425.onlineshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.mum.cs425.onlineshopping.entity.ProductCategory;
import java.util.List;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    // Some category
    List<ProductCategory> findByCategoryTypeInOrderByCategoryTypeAsc(List<Integer> categoryTypes);
    // All category
    List<ProductCategory> findAllByOrderByCategoryType();
    // One category
    ProductCategory findByCategoryType(Integer categoryType);
}
