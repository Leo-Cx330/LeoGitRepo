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
					// ���ѵȴ��̣߳� ����������.
					if (countDownLatch != null) {
						countDownLatch.countDown();
					}
				}
				
				public void handleDataChange(String path, Object data) throws Exception {
					
				}
			};
			
			// ע�ᵽzk������
			zkClient.subscribeDataChanges(PATH, iZkDataListener);
			if(zkClient.exists(PATH)){
				countDownLatch=new CountDownLatch(1);
				countDownLatch.await();
			}
			// ɾ���¼�֪ͨ
			zkClient.unsubscribeDataChanges(PATH, iZkDataListener);
		}catch(Exception e){
			
		}
		
	}

	@Override
	protected boolean tryLock() {
		try{
			System.out.println("��ʼ������");
			zkClient.createEphemeral(PATH);
			//����
			return true;
		}catch(Exception e){
			//ʧ��
			return false;
		}
		
	}

}
