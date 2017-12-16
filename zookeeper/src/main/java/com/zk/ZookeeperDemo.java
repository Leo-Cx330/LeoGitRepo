package com.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

@SuppressWarnings("unused")
public class ZookeeperDemo {

	private static final String CONNECT_ADDR = "192.168.75.132:2181,192.168.75.133:2181,192.168.75.136:2181";

	private static final int SESSION_OUTTIME = 2000;

	private static final CountDownLatch COUNT_DOWN_LATCH=new CountDownLatch(1);

	public static void main(String[] args) throws Exception {
		ZooKeeper zk=new ZooKeeper(CONNECT_ADDR, SESSION_OUTTIME, new Watcher() {

			public void process(WatchedEvent event) {
				KeeperState state = event.getState();
				EventType type = event.getType();
				//如果建立连接
				if(KeeperState.SyncConnected==state){
					if(EventType.None==type){
						COUNT_DOWN_LATCH.countDown();
						System.out.println("zk 建立连接");
					}
				}

			}
		});
		COUNT_DOWN_LATCH.await();

		String result = zk.create("/hh/jj", "hello".getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		System.out.println("result:"+result);
		 zk.close();
		System.out.println("hello");

	}
}
