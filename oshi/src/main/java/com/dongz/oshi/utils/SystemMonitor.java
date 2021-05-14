package com.dongz.oshi.utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/14 9:13 上午
 */
public class SystemMonitor {
    private final static Double OneM = 1024.0 * 1024;

    private final static SystemInfo systemInfo = new SystemInfo();

    public void init() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                System.out.println("cpu核数:" + getCPU());
                System.out.println("cpu系统使用率:" + new DecimalFormat("#.##%").format(getCPUUsed()));

                System.out.println("总的物理内存:"
                        + new DecimalFormat("#.##").format(getPhysicsTotalMemory())
                        + "M");
                System.out.println("剩余的物理内存:"
                        + new DecimalFormat("#.##").format(getPhysicsRemainingMemory())
                        + "M");
                System.out.println("已使用的物理内存:"
                        + new DecimalFormat("#.##").format(getPhysicsTotalMemory() - getPhysicsRemainingMemory())
                        + "M");

                System.out.println("初始的总内存(JVM):" + new DecimalFormat("#.#").format(getJVMOriginMemory()) + "M");
                System.out.println(
                        "最大可用内存(JVM):" + new DecimalFormat("#.#").format(getJVMMaxMemory()) + "M");
                System.out.println(
                        "已使用的内存(JVM):" + new DecimalFormat("#.#").format(getJVMUsedMemory()) + "M");

                System.out.println("===========================");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 6, TimeUnit.SECONDS);
    }

    /**
     * 物理总内存
     */
    public static Integer getPhysicsTotalMemory() {
        return new Double(systemInfo.getHardware().getMemory().getTotal() / OneM).intValue();
    }

    /**
     * 物理剩余内存
     */
    public static Integer getPhysicsRemainingMemory() {
        return new Double(systemInfo.getHardware().getMemory().getAvailable() / OneM).intValue();
    }

    /**
     * JVM 初始总内存
     */
    public static Integer getJVMOriginMemory() {
        MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();

        /**
         * 椎内存使用情况
         */
        MemoryUsage memoryUsage = memoryMxBean.getHeapMemoryUsage();
        return new Double(memoryUsage.getInit() / OneM).intValue();
    }

    /**
     * JVM 最大可用内存
     */
    public static Integer getJVMMaxMemory() {
        MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();

        /**
         * 椎内存使用情况
         */
        MemoryUsage memoryUsage = memoryMxBean.getHeapMemoryUsage();
        return new Double(memoryUsage.getMax() / OneM).intValue();
    }

    /**
     * JVM 最大可用内存
     */
    public static Integer getJVMUsedMemory() {
        MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();

        /**
         * 椎内存使用情况
         */
        MemoryUsage memoryUsage = memoryMxBean.getHeapMemoryUsage();
        return new Double(memoryUsage.getUsed() / OneM).intValue();
    }

    /**
     * cpu核心
     */
    public static int getCPU() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.getLogicalProcessorCount();
    }

    /**
     * cpu使用率
     */
    public static double getCPUUsed() throws InterruptedException {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 睡眠1s
        TimeUnit.SECONDS.sleep(1);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()]
                - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()]
                - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()]
                - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()]
                - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()]
                - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()]
                - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()]
                - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        return (cSys + user)/(totalCpu * 1.0);
    }

    public void use(float cpuUse, float memoryUse) {
        // 获取最大cpu线程
        int cpu = (int) Math.floor(getCPU() * (cpuUse < 0 ? 0 : (cpuUse > 1 ? 1: cpuUse)));
        // 获取最大内存
        int memory = (int) Math.floor(getPhysicsTotalMemory() * (memoryUse < 0 ? 0 : (memoryUse > 1 ? 1: memoryUse)));
        if (memory >= getJVMMaxMemory()) {
            memory = (int) Math.floor(getJVMMaxMemory() * 0.8);
        }

        Vector vector = new Vector();
        for (int i = 0; i < memory; i++) {
            vector.add(new byte[1024 * 1024]);
        }

        for (int i = 0; i < cpu; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    // 占有cpu
                }
            });
            thread.start();
        }
    }
}
