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
package cn.xiaosm.plainadmin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.exception.SQLOperateException;
import cn.xiaosm.plainadmin.mapper.MenuMapper;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.utils.MemoryUtils;
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
        Menu menu = null;
        if (Objects.nonNull( (menu = menuMapper.selectById(id)) )) {
            throw new SQLOperateException(
                    StrUtil.format("删除失败###{}下含有子菜单，本次删除操作已被取消", menu.getName())
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
     * 根据类型，清楚不必要的内容
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
        MemoryUtils.saveObject("MenuList", this.list());
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
                    .filter(el -> el.getParentMenu() == parentMenu)
                    .collect(Collectors.toList());

            return null;
        } else {
            return menuList.stream().filter(el -> el.getParentMenu() == parentMenu).collect(Collectors.toList());
        }
    }

    /**
     * 根据父菜单获取子菜单，以树的形式()
     * @param parentId
     * @return
     */
    @Override
    public List<Menu> getByParentIdOfTree(Integer parentId, boolean includeButton) {
        return this.buildTree(this.getAll(includeButton), parentId);
    }

    // @Override
    // public List<Menu> getByRoleId(Integer roleId) {
    //     List<Menu> menus = menuMapper.selectAllByRoleIds()
    //     return menus;
    // }

    /**
     * 根据角色id查询菜单 （全字段）
     * @param roleIds
     * @return
     */
    @Override
    public List<Menu> getByRoleIds(String roleIds) {
        return menuMapper.selectAllByRoleIds(roleIds);
    }

    @Override
    public List<Menu> buildTree(List<Menu> menuList, Integer parentId) {
        return this.buildTree(menuList, parentId, false);
    }

    /**
     * 构建菜单树，这里是先将父级菜单及其子菜单过滤出来
     * @param menuList
     * @param parentId
     * @param includeParent 是否包含父菜单
     * @return
     */
    @Override
    public List<Menu> buildTree(List<Menu> menuList, Integer parentId, boolean includeParent) {
        List<Menu> menuTree = menuList.stream()
                .filter(el -> includeParent ? el.getId() == parentId : el.getParentMenu() == parentId)
                // 过滤状态不是启用的，这里只要将父类别过滤了，子类别是添加不进的
                .filter(el-> el.getStatus() == 1)
                .collect(Collectors.toList());
        return this.buildTree(menuTree, menuList);
    }

    /**
     * 构建菜单树
     * @param menuTree 初始菜单树，需要有顶级菜单
     * @param menuList 链表菜单，通过遍历此菜单构建树
     * @return
     */
    private List<Menu> buildTree(List<Menu> menuTree, List<Menu> menuList) {
        if (menuList.isEmpty() || menuTree.isEmpty()) return menuTree;
        List<Menu> temp = new LinkedList<>();
        Iterator<Menu> it = null;
        Menu next;
        for (Menu menu : menuTree) {
            // 如果是按钮，将不会继续找他的孩子
            if (menu.getType() != 3) {
                it = menuList.iterator();
                while (it.hasNext()) {
                    next = it.next();
                    if (next.getParentMenu() == menu.getId()) {
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
     * @return
     */
    public List<Menu> getAll(boolean includeButton) {
        List<Menu> list = (List<Menu>) MemoryUtils.getObject("MenuList");
        list = list.stream()
                .filter(el -> includeButton ? el.getType() <= 3 : el.getType() < 3)
                .collect(Collectors.toList());
        if (Objects.isNull(list)) {
            list = this.list();
            MemoryUtils.saveObject("MenuList", list);
        }
        return list;
    }
}