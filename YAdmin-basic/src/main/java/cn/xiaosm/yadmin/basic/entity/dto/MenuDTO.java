/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MenuDTO
 * Author:   Young
 * Date:     2020/6/15 15:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.entity.dto;

import cn.xiaosm.yadmin.basic.entity.Menu;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/15
 * @since 1.0.0
 */
public class MenuDTO extends Menu {

    private boolean hasChildren = true;
    private List<Menu> children; // 子菜单

    public MenuDTO(Integer id, String name) {
        super(id, name);
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public MenuDTO setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
        return this;
    }

    @Override
    public List<Menu> getChildren() {
        return children;
    }

    @Override
    public MenuDTO setChildren(List<Menu> children) {
        this.children = children;
        return this;
    }
}