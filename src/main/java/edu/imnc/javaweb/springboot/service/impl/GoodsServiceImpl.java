package edu.imnc.javaweb.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.imnc.javaweb.springboot.entity.Goods;
import edu.imnc.javaweb.springboot.mapper.GoodsMapper;
import edu.imnc.javaweb.springboot.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods getGoodsById(Integer goodsId) {
        return goodsMapper.selectById(goodsId);
    }
}
