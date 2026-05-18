package edu.imnc.javaweb.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import edu.imnc.javaweb.springboot.entity.Goods;
import edu.imnc.javaweb.springboot.mapper.GoodsMapper;
import edu.imnc.javaweb.springboot.utils.ResponseJSON;

@RestController
@RequestMapping("/api")
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;

    @GetMapping("/goodses")
    public ResponseJSON getGoods(@RequestParam(value = "type", required = false) Integer type,
                                 @RequestParam(value = "search", required = false) String search) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        if (type != null) {
            queryWrapper.eq(Goods::getGtId, type);
        }
        if (search != null && !search.trim().isEmpty()) {
            String keyword = "%" + search.trim().toLowerCase() + "%";
            queryWrapper.and(wrapper -> wrapper
                    .apply("LOWER(goods_name) LIKE {0}", keyword)
                    .or()
                    .apply("LOWER(intro) LIKE {0}", keyword));
        }
        List<Goods> goodsList = goodsMapper.selectList(queryWrapper);
        return ResponseJSON.ok(goodsList);
    }
}
