/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MenuVO
 * Author:   Young
 * Date:     2020/6/19 16:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity.vo;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.NumberUtil;
import cn.xiaosm.plainadmin.entity.enums.StatusEnum;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/19
 * @since 1.0.0
 */
public class MenuVO {

    private Integer id;
    private Integer type;
    private String name;
    private int parentMenu;
    private Integer status;
    private String showType; // 数据展示格式默认为 链表
    private boolean includeButton; // 是否包含按钮

    public void setParentMenu(int parentMenu) {
        this.parentMenu = parentMenu < 0 ? 0 : parentMenu;
    }

    public void setShowType(String showType) {
        this.showType = StringUtils.isBlank(showType) ? "list" : showType;
    }

    public Integer getId() {
        return id;
    }

    public MenuVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public MenuVO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public MenuVO setName(String name) {
        this.name = name;
        return this;
    }

    public int getParentMenu() {
        return parentMenu;
    }

    public Integer getStatus() {
        return status;
    }

    public MenuVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getShowType() {
        return showType;
    }

    public boolean isIncludeButton() {
        return includeButton;
    }

    public MenuVO setIncludeButton(boolean includeButton) {
        this.includeButton = includeButton;
        return this;
    }
}