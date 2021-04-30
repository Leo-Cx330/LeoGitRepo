package com.leo.cloud.netty.nio;

/**
 * @Author leo
 * @Date 2021/3/14
 * @Desc
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8081;
        if (args != null && args.length > 0) {
            try{
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        TimeClientHandle timeClientHandle = new TimeClientHandle("127.0.0.1",port);
        new Thread(timeClientHandle,"NIO-TimeClientHandle-001").start();

    }
}
