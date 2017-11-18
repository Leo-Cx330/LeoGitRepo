package com.zk.order;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements	 Lock {
	//zookeeper��Ⱥ���ӵ�ַ
	private static final String CONNECT_ADDR = "192.168.75.132:2181,192.168.75.133:2181,192.168.75.136:2181";
	//zk�ͻ���
	protected ZkClient zkClient=new ZkClient(CONNECT_ADDR);
	
	protected String PATH="/lock";
	
	public void getLock(){	
		
		if(tryLock()){
			System.out.println("��ȡ��������Դ");
		}else{
			//�ȴ�
			waitLock();
			//���»�ȡ����Դ
			getLock();
		}
	}
	protected abstract void waitLock();
	protected abstract boolean tryLock();
	public void unLock() {
		if (zkClient != null) {
			zkClient.close();
		}
		System.out.println("�Ѿ��ͷ���...");
	}
	
}
