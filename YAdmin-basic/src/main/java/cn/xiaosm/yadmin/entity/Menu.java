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
package cn.xiaosm.yadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
@TableName("menu")
public class Menu extends BaseEntity implements Cloneable {



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

    public Integer getId() {
        return id;
    }

    public Menu setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Menu setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Menu setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getParentMenu() {
        return parentMenu;
    }

    public Menu setParentMenu(Integer parentMenu) {
        this.parentMenu = parentMenu;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Menu setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Menu setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Menu setPath(String path) {
        this.path = path;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public Menu setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public Menu setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Menu setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public Menu setChildren(List<Menu> children) {
        this.children = children;
        return this;
    }
}