package com.dongz.oshi;

import com.dongz.oshi.utils.SystemMonitor;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/14 9:13 上午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SystemMonitor monitor = new SystemMonitor();
        monitor.init();
        // 模拟占用 CPU 内存
        float cpuUse = Float.parseFloat(args[0]);
        float memoryUse = Float.parseFloat(args[1]);

        Thread.sleep(10000);
        monitor.use(cpuUse, memoryUse);
    }
}
