package in.aprilfish.service;

import in.aprilfish.mapper.User;
import in.aprilfish.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	@Transactional
	public User save(User user){
		userMapper.insertOne(user);
		log.info("new user id = {}",user.getId());

		return user;
	}

	public int updateByPK(User user){
		return userMapper.updateByPK(user);
	}

	public User findOne(Integer id){
		return userMapper.selectByPK(id);
	}

	@Transactional
	public void repeatableRead(){
		CountDownLatch writeDownLatch=new CountDownLatch(1);
		CountDownLatch readDownLatch=new CountDownLatch(1);

		log.info("第一次读取");
		User user=this.findOne(1);
		log.info("name={}",user.getName());
		writeDownLatch.countDown();

		long begin=System.currentTimeMillis();
		new WriteTask(writeDownLatch,readDownLatch,this).start();

	/*	new Thread(() -> {
			log.info("写线程等待第一次读取结束");
			log.info("开始写数据");
			readDownLatch.countDown();
			log.info("写入完成");
		}).start();*/

		try {
			//readDownLatch.await(3,TimeUnit.SECONDS);
			readDownLatch.await();
		}catch (Exception e){
			e.printStackTrace();
		}
		log.info("Time: {} seconds",(System.currentTimeMillis()-begin));

		log.info("等待写入完成，开始第二次读取");
		user=this.findOne(1);
		log.info("name={}",user.getName());
	}

}
