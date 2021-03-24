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
public class SystemInfoMy {

    private String osName;
    private Long runDateTime;
    private Double totalMemory;
    private Double usedMemory;
    private Long totalCpu;
    private Double usedCpu;
    private Integer cpuCount;
    private List<Disk> disks;

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