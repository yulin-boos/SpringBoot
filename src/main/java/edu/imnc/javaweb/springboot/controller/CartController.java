package edu.imnc.javaweb.springboot.controller;

import edu.imnc.javaweb.springboot.entity.CartItem;
import edu.imnc.javaweb.springboot.mapper.CartMapper;
import edu.imnc.javaweb.springboot.service.CartService;
import edu.imnc.javaweb.springboot.utils.ResponseJSON;
import edu.imnc.javaweb.springboot.vo.CartItemVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private HttpSession session;

    @GetMapping("/users/cart")
    public ResponseJSON getCartByUserId() {
        Integer userid = (Integer) session.getAttribute("userid");

        ArrayList<CartItemVO> myCart = cartMapper.findByUserId(userid);
        ResponseJSON rJson = ResponseJSON.ok(myCart);

        return rJson;
    }

    @PostMapping("/users/cart")
    public ResponseJSON addToCart(@ModelAttribute CartItem item) {
        Integer userid = (Integer) session.getAttribute("userid");
        item.setUserId(userid);

        int result = cartService.addToCart(item);

        ResponseJSON rJson = null;
        if (result == 1) {
            rJson = ResponseJSON.ok(null);
            rJson.setMessage("商品成功加入购物车");
        } else if (result == -1) {
            rJson = ResponseJSON.error();
            rJson.setMessage("商品加入购物车失败：选购数量超出库存");
        } else {
            rJson = ResponseJSON.error();
            rJson.setMessage("商品加入购物车异常");
        }

        return rJson;
    }
}
