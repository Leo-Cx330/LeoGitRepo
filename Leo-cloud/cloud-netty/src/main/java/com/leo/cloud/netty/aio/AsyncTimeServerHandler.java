package com.leo.cloud.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Author leo
 * @Date 2021/3/25
 * @Desc
 */
public class AsyncTimeServerHandler  implements Runnable{

    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port){
        this.port=port;
        try{
            asynchronousServerSocketChannel=AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is start in port:"+port);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        latch=new CountDownLatch(1);
        doAccept();
        try{
            latch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void doAccept(){
       // asynchronousServerSocketChannel.accept(this,new AcceptCompletionHandler());
    }
}
