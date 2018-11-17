package io.springrain.mapper;

import io.aprilfish.mybatis.query.Example;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserOrderMapper {

    UserOrder selectByPrimaryKey(Integer id);

    //List<UserOrder> selectByExample(UserOrderExample example);
    List<UserOrder> selectByExample(Example example);

    int countByExample(UserOrderExample example);

}