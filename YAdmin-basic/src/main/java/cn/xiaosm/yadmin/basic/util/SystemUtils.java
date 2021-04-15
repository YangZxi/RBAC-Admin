package cn.xiaosm.yadmin.basic.util;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hutool.system.SystemUtil;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import cn.xiaosm.yadmin.basic.entity.SystemInfo;

import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

/**
 * 系统监控
 */
public class SystemUtils {

    private static final Double KB = 1024.;
    private static final Double MB = KB * 1024;
    private static final Double GB = MB * 1024;

    public static SystemInfo info() {
        SystemInfo info = new SystemInfo();
        // 获取系统名称
        info.setOsName(SystemUtil.get(SystemUtil.OS_NAME));
        // 获取CPU信息
        CpuInfo cpuInfo = OshiUtil.getCpuInfo();
        info.setCpuCount(cpuInfo.getCpuNum());
        info.setFreeCpu(100 - cpuInfo.getFree());
        // 获取内存信息
        GlobalMemory memory = OshiUtil.getMemory();
        info.setTotalMemory(memory.getTotal() / GB);
        info.setUsedMemory((memory.getTotal() - memory.getAvailable()) / GB);
        // 获取磁盘信息
        File[] files = File.listRoots();
        for (File file : files) {
            info.addDisk(new SystemInfo.Disk(
                file.getPath(),
                file.getTotalSpace() / GB,
                (file.getTotalSpace() - file.getFreeSpace()) / GB)
            );
        }
        // 获取项目运行时间
        info.setRunDateTime(SystemUtil.getRuntimeMXBean().getStartTime());
        return info;
    }

}