package io.springrain.controller;

import io.springrain.mapper.User;
import io.springrain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public String index() {
	    User user = userMapper.selectByPK(4);
	    log.info("before:{}",user.toString());

	    user.setName("update_" + user.getName());

	    // updateByPK是从CrudMapper中继承而来的，UserMapper.xml中并没有声明该sql
	    userMapper.updateByPK(user);

	    user = userMapper.selectByPK(user.getId());
	    log.info("after:{}", user.toString());

        return "index";
    }

}
