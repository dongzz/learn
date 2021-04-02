package com.dongz.test;

import com.dongz.test.entity.User;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author dz <895180729@qq.com>
 * @Description 锁升级测试
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/3/1 10:58 上午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        User userTemp = new User();
        System.out.println("无状态（001）：" + ClassLayout.parseInstance(userTemp).toPrintable());

        // jvm默认延时4s自动开启偏向锁，可通过 -XX:BiasedLockingStartupDelay=0 取消延时；
        // 如果不要偏向锁，可通过 -XX:-UseBiasedLocking=false来设置
        Thread.sleep(5000);
        User user = new User();
        System.out.println("启动偏向锁（101）：" + ClassLayout.parseInstance(user).toPrintable());

        for (int i = 0; i < 2; i++) {
            synchronized (user) {
                System.out.println("偏向锁（101）（带线程id）：" + ClassLayout.parseInstance(user).toPrintable());
            }
            System.out.println("偏向锁释放（101）（带线程id）：" + ClassLayout.parseInstance(user).toPrintable());
        }

        new Thread(() -> {
            synchronized (user) {
                System.out.println("轻量级所（00）：" + ClassLayout.parseInstance(user).toPrintable());
                try {
                    System.out.println("睡眠3秒钟=========");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("轻量--> 重量（10）：" + ClassLayout.parseInstance(user).toPrintable());
            }
        }).start();

        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (user) {
                System.out.println("重量级锁（10）：" + ClassLayout.parseInstance(user).toPrintable());
            }
        }).start();
    }
}
