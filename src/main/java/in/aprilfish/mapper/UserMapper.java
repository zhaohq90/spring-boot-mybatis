package in.aprilfish.mapper;

import in.aprilfish.mybatis.mapper.CrudMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 继承CrudMapper，就自动拥有CRUD方法
 */
@Component
public interface UserMapper extends CrudMapper<User> {

    List<User> select(User user);

}
