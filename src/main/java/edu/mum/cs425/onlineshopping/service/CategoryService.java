package edu.mum.cs425.onlineshopping.service;

import edu.mum.cs425.onlineshopping.entity.ProductCategory;
import java.util.List;


public interface CategoryService {

    List<ProductCategory> findAll();

    ProductCategory findByCategoryType(Integer categoryType);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
