package edu.imnc.javaweb.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("goods")
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String goodsName;
    private Integer gtId;
    private BigDecimal price;
    private Integer stock;
    private String intro;
}
