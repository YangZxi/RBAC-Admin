/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: Menu
 * Author:   Young
 * Date:     2020/6/14 14:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
@Data
@TableName("menu")
public class Menu extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; // 菜单名称
    private Integer type; // 菜单类型
    private Integer parentMenu; // 上级菜单
    private String icon; // 菜单图标
    @TableField(value = "`order`")
    private Integer order; // 排序
    private String path; // 组件路径
    private String component; // 组件名称
    private String permission; // 权限许可
    private Integer status; // 状态
    @TableField(exist = false)
    private List<Menu> children; // 子菜单

}