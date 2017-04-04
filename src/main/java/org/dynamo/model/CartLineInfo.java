package org.dynamo.model;

import java.math.BigDecimal;

import org.dynamo.entity.Product;

public class CartLineInfo {

    private Product product;
    private int quantity;
 
    public CartLineInfo() {
        this.quantity = 0;
    }
 
    public int getQuantity() {
        return quantity;
    }
 
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
 
    public BigDecimal getAmount() {
        return this.product.getPrice().multiply(new BigDecimal(this.quantity));
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}