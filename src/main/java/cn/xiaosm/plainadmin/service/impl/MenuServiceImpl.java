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

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.json.JSONUtil;
import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.mapper.MenuMapper;
import cn.xiaosm.plainadmin.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

    @Override
    public List<Menu> getByRoleId(String roleIds) {
        List<Menu> menus = menuMapper.selectAllByRoleId(roleIds);
        return menus;
    }

    @Override
    public List<Menu> buildTree(List<Menu> menuList) {
        List<Menu> menuTree = menuList.stream().filter(el -> el.getParentMenu() == 0)
                .collect(Collectors.toList());
        return this.buildTree(menuTree, menuList);
    }

    private List<Menu> buildTree(List<Menu> menuTree, List<Menu> menuList) {
        if (menuList.isEmpty() || menuTree.isEmpty()) return menuList;
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