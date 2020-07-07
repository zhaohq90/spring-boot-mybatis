package in.aprilfish;

import in.aprilfish.mapper.UserOrder;
import in.aprilfish.mapper.UserOrderMapper;
import in.aprilfish.mybatis.query.LambdaQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class LambdaQueryTests extends AbstractTest {

	@Autowired
	private UserOrderMapper userOrderMapper;

	@Test
	public void selectByLambdaQuery() {
		LambdaQuery query = new LambdaQuery();
		query.eq(UserOrder::getId, 1)
				.like(UserOrder::getName, "admin");

		List<UserOrder> userOrders = userOrderMapper.selectByExample(query);

		Assert.assertEquals("admin", userOrders.get(0).getName());
	}

}
