package edu.imnc.javaweb.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.imnc.javaweb.springboot.entity.CartItem;
import edu.imnc.javaweb.springboot.mapper.CartMapper;
import edu.imnc.javaweb.springboot.mapper.GoodsMapper;
import edu.imnc.javaweb.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, CartItem> implements CartService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int addToCart(CartItem item) {
        int result = 0;

        int stock = goodsMapper.selectById(item.getGoodsId()).getStock();

        CartItem ci = query().eq("user_id", item.getUserId()).eq("goods_id", item.getGoodsId()).one();
        if (ci == null) {
            if (item.getAmount() > stock) {
                result = -1;
            } else {
                boolean b = save(item);
                if (b) {
                    result = 1;
                }
            }
        } else {
            int amount = ci.getAmount() + item.getAmount();
            if (amount > stock) {
                result = -1;
            } else {
                item.setAmount(amount);
                item.setCartId(ci.getCartId());
                boolean b = updateById(item);
                if (b) {
                    result = 1;
                }
            }
        }

        return result;
    }
}
