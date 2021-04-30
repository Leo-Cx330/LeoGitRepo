package com.leo.cloud.netty.aio;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Author leo
 * @Date 2021/3/29
 * @Desc
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousServerSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousServerSocketChannel result, AsyncTimeServerHandler attachment) {
        //attachment.asynchronousServerSocketChannel.accept(attachment,this);
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {

    }
}
