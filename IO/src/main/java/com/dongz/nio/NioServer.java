package com.dongz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dz <895180729@qq.com>
 * @Description 异步非阻塞
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/3/2 5:49 下午
 */
public class NioServer {
    /**保存客户端连接
     */
    static List<SocketChannel> channels = new ArrayList<>();;

    public static void main(String[] args) throws IOException {
        // 创建NIO ServerSocketChannel， 与 BIO的ServerSocket类似
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress(9000));
        // 设置ServerSocketChannel为非阻塞
        channel.configureBlocking(false);
        System.out.println("服务启动成功");

        while (true) {
            // 非阻塞模式Accept方法不会阻塞，否则会阻塞
            // NIO的非阻塞是由操作系统内部实现的， 底层调用了Linux内核的accept函数
            SocketChannel socketChannel = channel.accept();
            // 如果有和互动进行连接
            if (socketChannel != null) {
                System.out.println("连接成功");
                // 设置SocketChannel为非阻塞
                socketChannel.configureBlocking(false);
                // 保存客户端连接在list中， 会有性能问题
                channels.add(socketChannel);
            }

            // 遍历连接进行数据读取
            Iterator<SocketChannel> iterator = channels.iterator();
            while (iterator.hasNext()) {
                SocketChannel sc = iterator.next();
                ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                //非阻塞模式read方法不会阻塞，否则会阻塞
                int len = sc.read(byteBuffer);
                // 如果有数据
                if (len > 0) {
                    System.out.println("接收到消息：" + new String(byteBuffer.array()));
                } else if (len == -1) {
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }
            }
        }
    }
}
