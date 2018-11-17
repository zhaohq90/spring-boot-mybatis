package io.springrain;

import io.springrain.mapper.UserOrder;
import io.springrain.mapper.UserOrderExample;
import io.springrain.mapper.UserOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserOrderMapperTest extends AbstractTest{

    @Autowired
    private UserOrderMapper userOrderMapper;

    /**
     * 测试标准mapper.xml功能
     * 测试级联功能
     */
    @Test
    public void selectUserOrderWithUser(){
        UserOrder userOrder=userOrderMapper.selectByPrimaryKey(1);
        log.info(userOrder.toString());
        Assert.assertNotNull(userOrder.getUser());
        log.info(userOrder.getUser().toString());
    }

    @Test
    public void selectByExample(){
        List<Integer> list=new ArrayList<>();
        list.add(1);
        list.add(2);

        UserOrderExample userOrderExample=new UserOrderExample();
        userOrderExample.name.like("ki").uid.in(list);

        List<UserOrder> userOrders=userOrderMapper.selectByExample(userOrderExample);

        Assert.assertEquals("niki", userOrders.get(0).getName());
    }

}
