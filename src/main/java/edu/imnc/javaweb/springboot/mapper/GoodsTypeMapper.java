package edu.imnc.javaweb.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.imnc.javaweb.springboot.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
}
