package com.leo.cloud.netty.nio;

/**
 * @Author leo
 * @Date 2021/3/12
 * @Desc
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8081;
        if (args != null && args.length > 0) {
            try{
                port=Integer.valueOf(args[0]);
            }catch (NumberFormatException e){

            }
        }
        MultiplexerServer multiplexerServer = new MultiplexerServer(port);
        new Thread(multiplexerServer,"NIO-MultiplexerServer-001").start();

    }
}
