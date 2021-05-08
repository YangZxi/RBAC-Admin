package cn.xiaosm.yadmin;

import cn.hutool.system.SystemUtil;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import cn.xiaosm.yadmin.basic.entity.SystemInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import oshi.hardware.GlobalMemory;

import java.io.File;

/**
 * @author Young
 * @create 2021/4/15
 * @since 1.0.0
 */
@SpringBootTest
public class OshiTest {

    private static final Double KB = 1024.;
    private static final Double MB = KB * 1024;
    private static final Double GB = MB * 1024;

    @Test
    public void test1() {
        SystemInfo info = new SystemInfo();
        // 获取系统名称
        info.setOsName(SystemUtil.OS_NAME);
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
        System.out.printf(info.toString());
    }

}