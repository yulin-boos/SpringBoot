package edu.imnc.javaweb.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.imnc.javaweb.springboot.entity.CartItem;

public interface CartService extends IService<CartItem> {
    int addToCart(CartItem item);
}
