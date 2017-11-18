package com.zk.order;

import java.util.concurrent.CountDownLatch;

import org.I0Itec.zkclient.IZkDataListener;

public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock{

	private CountDownLatch countDownLatch = new CountDownLatch(1);

	
	@Override
	protected void waitLock() {
		try{
			IZkDataListener iZkDataListener=new IZkDataListener() {
				
				public void handleDataDeleted(String path) throws Exception {
					// 唤醒等待线程， 继续往下走.
					if (countDownLatch != null) {
						countDownLatch.countDown();
					}
				}
				
				public void handleDataChange(String path, Object data) throws Exception {
					
				}
			};
			
			// 注册到zk监听中
			zkClient.subscribeDataChanges(PATH, iZkDataListener);
			if(zkClient.exists(PATH)){
				countDownLatch=new CountDownLatch(1);
				countDownLatch.await();
			}
			// 删除事件通知
			zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
		}catch(Exception e){
			
		}
		
	}

	@Override
	protected boolean tryLock() {
		try{
			System.out.println("开始创建锁");
			zkClient.createEphemeral(PATH);
			//创建
			return true;
		}catch(Exception e){
			//失败
			return false;
		}
		
	}

}
