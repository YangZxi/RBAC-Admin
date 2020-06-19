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

import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.mapper.MenuMapper;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.utils.MemoryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity addEntity(Menu menu) {
        return null;
    }

    @Override
    public ResponseEntity deleteEntity(Menu menu) {
        return null;
    }

    @Override
    public ResponseEntity modifyEntity(Menu menu) {
        return null;
    }

    @Override
    public Page<Menu> listOfPage(Page<Menu> page, QueryWrapper<Menu> queryWrapper) {
        return null;
    }

    /**
     * 获取所有的菜单
     * @return
     */
    public List<Menu> getAll() {
        List<Menu> list = (List<Menu>) MemoryUtils.getObject("MenuList");
        list = list.stream().filter(el -> Objects.nonNull(el.getId())).collect(Collectors.toList());
        if (Objects.isNull(list)) {
            list = this.list();
            MemoryUtils.saveObject("MenuList", list);
        }
        return list;
    }

    @Override
    public List<Menu> getByParentId(Integer parentId) {
        return this.getByParentId(parentId, false);
    }

    /**
     * 根据父级菜单获取子菜单
     * @param parentMenu 父级菜单 id
     * @param recursive 是否递归获取子级的子级
     * @return
     */
    @Override
    public List<Menu> getByParentId(Integer parentMenu, boolean recursive) {
        List<Menu> menuList = this.getAll();
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
    public List<Menu> getByParentIdOfTree(Integer parentId) {
        /**
         *
         */
        return this.buildTree(this.getAll(), parentId);
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
            // 排除按钮
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
}