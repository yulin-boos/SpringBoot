package edu.imnc.javaweb.springboot.controller;

import edu.imnc.javaweb.springboot.entity.GoodsType;
import edu.imnc.javaweb.springboot.mapper.GoodsTypeMapper;
import edu.imnc.javaweb.springboot.utils.ResponseJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @GetMapping("/goodstypes")
    public ResponseJSON getAllGoodsType() {
        List<GoodsType> typeList = goodsTypeMapper.selectList(null);
        return ResponseJSON.ok(typeList);
    }
}
