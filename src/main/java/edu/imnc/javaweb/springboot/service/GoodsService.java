package edu.imnc.javaweb.springboot.service;

import edu.imnc.javaweb.springboot.entity.Goods;

public interface GoodsService {
    Goods getGoodsById(Integer goodsId);
}
