package edu.imnc.javaweb.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.imnc.javaweb.springboot.entity.CartItem;
import edu.imnc.javaweb.springboot.vo.CartItemVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CartMapper extends BaseMapper<CartItem> {
    @Select("select c.id as cartId,c.goods_id,c.amount,g.goods_name,g.price " +
            "from cart c left join goods g on c.goods_id=g.id " +
            "where c.user_id=#{userId}")
    ArrayList<CartItemVO> findByUserId(Integer userId);
}
