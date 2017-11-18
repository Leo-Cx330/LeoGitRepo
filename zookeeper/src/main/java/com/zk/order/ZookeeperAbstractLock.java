package com.zk.order;

import org.I0Itec.zkclient.ZkClient;

public abstract class ZookeeperAbstractLock implements	 Lock {
	//zookeeper集群连接地址
	private static final String CONNECT_ADDR = "192.168.75.132:2181,192.168.75.133:2181,192.168.75.136:2181";
	//zk客户端
	protected ZkClient zkClient=new ZkClient(CONNECT_ADDR);
	
	protected String PATH="/lock";
	
	public void getLock(){	
		
		if(tryLock()){
			System.out.println("获取到锁的资源");
		}else{
			//等待
			waitLock();
			//重新获取锁资源
			getLock();
		}
	}
	protected abstract void waitLock();
	protected abstract boolean tryLock();
	public void unLock() {
		if (zkClient != null) {
			zkClient.close();
		}
		System.out.println("已经释放锁...");
	}
	
}
