/**
 * Copyright: 2019-2020
 * FileName: SystemInfo
 * Author:   Young
 * Date:     2020/6/30 8:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/30
 * @since 1.0.0
 */
@Data
public class SystemInfo {

    // 系统名称
    private String osName;
    // 项目启动时间
    private Long runDateTime;
    // 总内存
    private Double totalMemory;
    // 已使用的内存
    private Double usedMemory;
    // cpu空闲率
    private Double freeCpu;
    // cpu核心数
    private Integer cpuCount;
    // 虚拟磁盘
    private List<Disk> disks;

    public void addDisk(Disk disk) {
        if (disks == null) disks = new ArrayList<Disk>();
        disks.add(disk);
    }

    public void setRunDateTime(long runDateTime) {
        this.runDateTime = new Date().getTime() - runDateTime;
    }

    @Data
    @AllArgsConstructor
    public static class Disk {
        private String name;
        private Double total;
        private Double used;
    }

}