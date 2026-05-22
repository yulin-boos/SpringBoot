package edu.imnc.javaweb.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart")
public class CartItem {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer cartId;
    private Integer userId;
    private Integer goodsId;
    private int amount;
}
