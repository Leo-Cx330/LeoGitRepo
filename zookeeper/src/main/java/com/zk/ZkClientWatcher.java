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
	//zookeeper��Ⱥ���ӵ�ַ
	private static final String CONNECT_ADDR = "192.168.75.132:2181,192.168.75.133:2181,192.168.75.136:2181";
	// �Ự��ʱʱ��
	private static final int SESSION_OUTTIME = 2000;
	
	private static final CountDownLatch COUNT_DOWN_LATCH=new CountDownLatch(1);
	
	private ZooKeeper zk;
	
	public void createConnection(String connectAddres, int sessionTimeOut){
		try{
			zk=new ZooKeeper(connectAddres, SESSION_OUTTIME, this);
			System.out.println("zk ��ʼ�������ӷ�����....");
			COUNT_DOWN_LATCH.await();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * �����ڵ�
	 * @param path
	 * @param data
	 * @return
	 */
	public boolean createPath(String path, String data) {
		try {
			this.exists(path, true);
			this.zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("�ڵ㴴���ɹ�, Path:" + path + ",data:" + data);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * �ж�ָ���ڵ��Ƿ����
	 * 
	 * @param path
	 *            �ڵ�·��
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
		
		// ��ȡ�¼�״̬
		KeeperState keeperState = watchedEvent.getState();
		// ��ȡ�¼�����
		EventType type = watchedEvent.getType();
		String path = watchedEvent.getPath();
		System.out.println("���뵽 process() keeperState:" + keeperState + ", eventType:" + type + ", path:" + path);
		// �ж��Ƿ�������
				if (KeeperState.SyncConnected == keeperState) {
					if (EventType.None == type) {
						// ������������ɹ�,�ú����������
						System.out.println("zk �������ӳɹ�!");
						COUNT_DOWN_LATCH.countDown();
					} else if (EventType.NodeCreated == type) {
						System.out.println("�¼�֪ͨ,����node�ڵ�" + path);
					} else if (EventType.NodeDataChanged == type) {
						System.out.println("�¼�֪ͨ,��ǰnode�ڵ�" + path + "���޸�....");
					}else if(EventType.NodeDeleted==type){
						System.out.println("�¼�֪ͨ,��ǰnode�ڵ�" + path + "��ɾ��....");
					}
				}
	}
	
	public static void main(String[] args) {
		ZkClientWatcher zkClientWatcher = new ZkClientWatcher();
		zkClientWatcher.createConnection(CONNECT_ADDR, SESSION_OUTTIME);
		boolean createResult = zkClientWatcher.createPath("/zk", "������");
		
	}
}



