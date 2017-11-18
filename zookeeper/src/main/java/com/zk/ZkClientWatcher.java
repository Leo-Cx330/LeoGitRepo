package com.zk;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

@SuppressWarnings("unused")
public class ZkClientWatcher implements Watcher{
	//zookeeper集群连接地址
	private static final String CONNECT_ADDR = "192.168.75.132:2181,192.168.75.133:2181,192.168.75.136:2181";
	// 会话超时时间
	private static final int SESSION_OUTTIME = 2000;
	
	private static final CountDownLatch COUNT_DOWN_LATCH=new CountDownLatch(1);
	
	private ZooKeeper zk;
	
	public void createConnection(String connectAddres, int sessionTimeOut){
		try{
			zk=new ZooKeeper(connectAddres, SESSION_OUTTIME, this);
			System.out.println("zk 开始启动连接服务器....");
			COUNT_DOWN_LATCH.await();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 创建节点
	 * @param path
	 * @param data
	 * @return
	 */
	public boolean createPath(String path, String data) {
		try {
			this.exists(path, true);
			this.zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("节点创建成功, Path:" + path + ",data:" + data);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 判断指定节点是否存在
	 * 
	 * @param path
	 *            节点路径
	 */
	public Stat exists(String path, boolean needWatch) {
		try {
			return this.zk.exists(path, needWatch);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void process(WatchedEvent watchedEvent) {
		
		// 获取事件状态
		KeeperState keeperState = watchedEvent.getState();
		// 获取事件类型
		EventType type = watchedEvent.getType();
		String path = watchedEvent.getPath();
		System.out.println("进入到 process() keeperState:" + keeperState + ", eventType:" + type + ", path:" + path);
		// 判断是否建立连接
				if (KeeperState.SyncConnected == keeperState) {
					if (EventType.None == type) {
						// 如果建立建立成功,让后程序往下走
						System.out.println("zk 建立连接成功!");
						COUNT_DOWN_LATCH.countDown();
					} else if (EventType.NodeCreated == type) {
						System.out.println("事件通知,新增node节点" + path);
					} else if (EventType.NodeDataChanged == type) {
						System.out.println("事件通知,当前node节点" + path + "被修改....");
					}else if(EventType.NodeDeleted==type){
						System.out.println("事件通知,当前node节点" + path + "被删除....");
					}
				}
	}
	
	public static void main(String[] args) {
		ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
		zkClientWatcher.createConnection(CONNECT_ADDR, SESSION_OUTTIME);
		boolean createResult = zkClientWatcher.createPath("/zk", "李云龙");
		
	}
}



