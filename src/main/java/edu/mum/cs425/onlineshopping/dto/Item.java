package edu.mum.cs425.onlineshopping.dto;

import edu.mum.cs425.onlineshopping.entity.ProductInfo;
import lombok.Data;


@Data
public class Item {
    private ProductInfo productInfo;

    private Integer quantity;

    public Item(ProductInfo productInfo, Integer quantity) {
        this.productInfo = productInfo;
        this.quantity = quantity;
    }

	public ProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}
