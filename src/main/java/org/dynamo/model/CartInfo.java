package org.dynamo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dynamo.entity.Product;

public class CartInfo {
	 
    private int orderNum;
 
    private CustomerInfo customerInfo;
 
    private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
 
    public CartInfo() {
 
    }
 
    public int getOrderNum() {
        return orderNum;
    }
 
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
 
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }
 
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
 
    public List<CartLineInfo> getCartLines() {
        return this.cartLines;
    }
 
    private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo line : this.cartLines) {
            if (line.getProduct().getCode().equals(code)) {
                return line;
            }
        }
        return null;
    }
 
    public void addProduct(Product product, int quantity) {
        CartLineInfo line = this.findLineByCode(product.getCode());
 
        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            //line.setProductInfo(productInfo);
            line.setProduct(product);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }
// 
//    public void validate() {
// 
//    }
// 
    public void updateProduct(String code, int quantity) {
        CartLineInfo line = this.findLineByCode(code);
 
        if (line != null) {
            if (quantity <= 0) {
                this.cartLines.remove(line);
            } else {
                line.setQuantity(quantity);
            }
        }
    }
 
    public void removeProduct(Product productInfo) {
        CartLineInfo line = this.findLineByCode(productInfo.getCode());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }
 
    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }
 
    public boolean isValidCustomer() {
        return this.customerInfo != null;
    }
 
    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }
 
    public BigDecimal getAmountTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartLineInfo line : this.cartLines) {
        	total = total.add(line.getAmount());
        }        
        return total;
    }
 
    public void updateQuantity(CartInfo cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();            
            for (CartLineInfo line : lines) {            	
                this.updateProduct(line.getProduct().getCode(), line.getQuantity());
            }
        }
    }
 
}
