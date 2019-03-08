package in.aprilfish.service;

import in.aprilfish.mapper.User;
import in.aprilfish.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaohq
 * @version 创建时间：2019/3/7
 */
@Slf4j
public class WriteTask extends Thread {

	private CountDownLatch writeDownLatch;

	private CountDownLatch readDownLatch;

	private UserService userService;

	public WriteTask(CountDownLatch writeDownLatch,CountDownLatch readDownLatch,UserService userService){
		this.userService=userService;
		this.writeDownLatch=writeDownLatch;
		this.readDownLatch=readDownLatch;
	}

	@Override
	public void run(){
		log.info("写线程等待第一次读取结束");
		try {
			writeDownLatch.await();
		}catch (Exception e){
			e.printStackTrace();
		}
		log.info("开始写数据");
		User user=userService.findOne(1);
		user.setName("jay");

		userService.updateByPK(user);
		try {
			TimeUnit.SECONDS.sleep(2);
		}catch (Exception e){
			e.printStackTrace();
		}
		readDownLatch.countDown();
		log.info("写入完成");
	}


}
