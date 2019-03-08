package in.aprilfish;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhaohq
 * @version 创建时间：2019/3/7
 */
@Slf4j
public class Tes {

	public static void main2(String[] args) {

	}

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(1);
final 		 CountDownLatch readDownLatch=new CountDownLatch(1);

		new Thread(){
			public void run() {

			};
		}.start();

		new Thread(()->{
			try {
				System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
				Thread.sleep(3000);
				System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
				latch.countDown();
				readDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();



		try {
			System.out.println("等待2个子线程执行完毕...");
			latch.await();
			readDownLatch.await();
			System.out.println("2个子线程已经执行完毕");
			System.out.println("继续执行主线程");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
