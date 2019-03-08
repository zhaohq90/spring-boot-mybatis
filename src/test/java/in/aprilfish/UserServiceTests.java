package in.aprilfish;

import in.aprilfish.mapper.User;
import in.aprilfish.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class UserServiceTests extends AbstractTest {

	@Autowired
	private UserService userService;

	@Test
	@Rollback(false)
	@Transactional
	public void testSave(){
		User user=new User();
		user.setId(1L);
		user.setName("admin");
		user.setCreateDate(new Date());

		userService.save(user);

		if(user.getId()>2){
			//throw new RuntimeException("err");
		}
	}

	/**
	 * 测试可重复读
	 */
	@Test
	@Rollback(false)
	//@Transactional
	public void repeatableRead(){
		userService.repeatableRead();
	}

}
