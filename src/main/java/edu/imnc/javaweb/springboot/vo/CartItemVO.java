package edu.imnc.javaweb.springboot.vo;

import lombok.Data;

@Data
public class CartItemVO {
    private Integer cartId;
    private Integer goodsId;
    private String goodsName;
    private double price;
    private int amount;
}
