/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MenuService
 * Author:   Young
 * Date:     2020/6/16 10:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.xiaosm.yadmin.basic.entity.Menu;
import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.enums.MenuType;
import cn.xiaosm.yadmin.basic.exception.SQLOperateException;
import cn.xiaosm.yadmin.basic.mapper.MenuMapper;
import cn.xiaosm.yadmin.basic.service.MenuService;
import cn.xiaosm.yadmin.basic.util.CacheUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private static final Integer ROOT_ID = 1;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public ResponseBody getById(Integer id) {
        return null;
    }

    @Override
    public boolean addEntity(Menu menu) {
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        this.clearAttr(menu);
        try {
            return this.save(menu);
        } catch (Exception e) {
            throw new SQLOperateException(menu.getPermission() + "已存在，请勿重复添加");
        }
    }

    @Override
    public boolean removeEntity(Menu menu) {

        return false;
    }

    @Override
    @Transactional
    public int removeById(Integer id) {
        List<Menu> list = this.list(new QueryWrapper<Menu>().eq("parent_menu", id));
        if (!list.isEmpty()) {
            throw new SQLOperateException(
                    StrUtil.format("删除失败###{}此菜单下含有子菜单，本次删除操作已被取消")
            );
        }
        int i = menuMapper.deleteById(id);
        return i;
    }

    @Override
    public int removeByIds(Set<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            count += this.removeById(id);
        }
        return count;
    }

    @Override
    public boolean modifyEntity(Menu menu) {
        menu.setUpdateTime(new Date());
        return this.updateById(menu);
    }

    @Override
    public Page<Menu> listOfPage(Page<Menu> page, QueryWrapper<Menu> queryWrapper) {
        return null;
    }

    @Override
    public List<Menu> getByParentId(Integer parentId) {
        return this.getByParentId(parentId, false);
    }

    /**
     * 根据类型，清除不必要的内容
     * @param menu
     */
    // @SneakyThrows
    public void clearAttr(Menu menu) {
        if (menu.getType() == 1) {
            menu.setComponent(null);
        } else if (menu.getType() == 2) {

        } else {
            if (Objects.nonNull(menuMapper.selectOne(new QueryWrapper<Menu>(menu, "permission")))) {

            }
            menu.setComponent(null);
            menu.setPath(null);
        }
    }

    /**
     * 刷新菜单
     */
    @Override
    public void refreshMenus() {
        CacheUtils.saveObject("MenuList", this.list());
    }

    /**
     * 根据父级菜单获取子菜单
     * @param parentMenu 父级菜单 id
     * @param recursive 是否递归获取子级的子级
     * @return
     */
    @Override
    public List<Menu> getByParentId(Integer parentMenu, boolean recursive) {
        List<Menu> menuList = this.getAll(true);
        if (recursive) {
            // 获取第一级子菜单
            List<Menu> reList = menuList.stream()
                    .filter(el -> el.getParentMenuId() == parentMenu)
                    .collect(Collectors.toList());
            for (Menu menu : reList) {

            }
            return null;
        } else {
            return menuList.stream().filter(el -> el.getParentMenuId() == parentMenu).collect(Collectors.toList());
        }
    }

    /**
     * 根据父菜单获取子菜单，以树的形式()
     * @param parentId
     * @return
     */
    @Override
    public List<Menu> getByParentIdOfTree(Integer parentId, boolean includeButton) {
        parentId = parentId == null || parentId < ROOT_ID ? ROOT_ID : parentId;
        return this.buildTree(this.getAll(includeButton), parentId);
    }

    /**
     * 根据角色id查询菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Menu> getByRoleId(Integer roleId) {
        return roleId == 1 ?
            this.getAll(true) : menuMapper.selectAllByRoleIds(String.valueOf(roleId));
    }

    /**
     * 根据角色id查询菜单
     * (查询不包括 根目录)
     *
     * @param roleIds 1,2,3
     * @return
     */
    @Override
    public List<Menu> getByRoleIds(String roleIds) {
        return menuMapper.selectAllByRoleIds(roleIds);
    }

    /**
     * 构建菜单树 （默认不包含父级菜单）
     * @param menuList
     * @return
     */
    @Override
    public List<Menu> buildTree(List<Menu> menuList) {
        return this.buildTree(menuList, ROOT_ID, false);
    }

    /**
     * 构建菜单树 （默认不包含父级菜单）
     *
     * @param menuList
     * @param parentId
     * @return
     */
    @Override
    public List<Menu> buildTree(List<Menu> menuList, Integer parentId) {
        return this.buildTree(menuList, parentId, false);
    }

    /**
     * 构建菜单树，这里是先将父级菜单及其子菜单过滤出来
     *
     * @param menuList
     * @param parentId
     * @param includeParent 是否包含父菜单
     * @return
     */
    @Override
    public List<Menu> buildTree(List<Menu> menuList, Integer parentId, boolean includeParent) {
        List<Menu> menuTree = menuList.stream()
                .filter(el -> includeParent ? el.getId() == parentId : el.getParentMenuId() == parentId)
                // 过滤非启用状态的菜单
                .filter(el-> el.getStatus() == 1)
                .collect(Collectors.toList());
        return this.buildTree(menuTree, menuList);
    }

    /**
     * 构建菜单树
     * @param menuTree 初始菜单树，需要有顶级菜单（整理后的菜单）
     * @param menuList 链表菜单，通过遍历此菜单构建树（未整理的菜单）
     * @return
     */
    private List<Menu> buildTree(List<Menu> menuTree, List<Menu> menuList) {
        if (menuList.isEmpty() || menuTree.isEmpty()) return menuTree;
        List<Menu> temp = new LinkedList<>();
        Iterator<Menu> it = null;
        Menu next;
        for (Menu menu : menuTree) {
            // 如果是按钮，将不会继续找它的子级菜单
            if (menu.getType() == MenuType.BUTTON.getType()) continue;
            else {
                it = menuList.iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if (next.getParentMenuId() == menu.getId()) {
                        next.setParent(menu);
                        temp.add(next);
                        it.remove();
                    }
                }
                menu.setChildren(temp);
                temp = new LinkedList<>();
                buildTree(menu.getChildren(), menuList);
            }
        }
        return menuTree;
    }

    /**
     * 获取所有的菜单
     * @param includePermission 是否包含权限
     * @return
     */
    @Override
    public List<Menu> getAll(boolean includePermission) {
        List<Menu> list = (List<Menu>) CacheUtils.getObject("MenuList");
        if (Objects.isNull(list)) {
            list = menuMapper.selectList(new QueryWrapper<Menu>().eq("status", 1));
            CacheUtils.saveObject("MenuList", list);
        }
        // 按照规则取出菜单
        if (!includePermission) {
            list = list.stream()
                .filter(el -> el.getType() != MenuType.BUTTON.getType())
                .collect(Collectors.toList());
        }
        return list;
    }
}