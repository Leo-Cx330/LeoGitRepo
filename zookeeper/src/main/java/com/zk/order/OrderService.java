package com.zk.order;

public class OrderService implements  Runnable{

	private Lock lock=new ZookeeperDistrbuteLock();
	
	private OrderNumGenerator generator=new OrderNumGenerator();
	
	public void run() {
		getNumber();
	}
	
	public void getNumber(){
		
		lock.getLock();
		String number=generator.getOrderNum();
		System.out.println(Thread.currentThread().getName()+"Éú³É¶©µ¥ºÅ:"+number);
		lock.unLock();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new OrderService()).start();
		}
	}
}