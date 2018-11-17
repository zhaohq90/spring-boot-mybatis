package io.springrain;

import io.springrain.mapper.User;
import io.springrain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserMapperTest extends AbstractTest{

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试sql优先级
     * 注：maven(测试)环境中不会将UserMapper.xml复制到classpath下，所以需要手动将该文件复制到相应目录
     */
    @Test
    public void selectByPK(){
        User user = userMapper.selectByPK(1);
        log.info(user.toString());
        Assert.assertEquals(new Long(4), user.getId());
    }

    /**
     * 测试CrudMapper中的方法
     */
    @Test
    public void updateByPK(){
        User user = userMapper.selectByPK(4);
        log.info("before:{}",user.toString());

        String newName="update_" + user.getName();
        user.setName(newName);

        //updateByPK是从CrudMapper中继承而来的，UserMapper.xml中并没有声明该sql
        userMapper.updateByPK(user);

        user = userMapper.selectByPK(user.getId());
        log.info("after:{}", user.toString());

        user = userMapper.selectByPK(4);
        Assert.assertEquals(newName,user.getName());
    }

    /**
     * 根据对象查询记录
     * 使用该方法时应保证实体类中无原始类型（如id应使用引用类型），否则mybatis调用getter方法取到默认值，查询条件与预期不符
     * xml未对空串做判断，空串也是合法字符串，可以在查询中做筛选，应用中如果要表达“空”应使用null
     */
    @Test
    public void selectByEntity() throws Exception{
        User user=new User();
        user.setName("aa");

        List<User> users=userMapper.select(user);

        Assert.assertEquals(new Long(1), users.get(0).getId());
    }

}
