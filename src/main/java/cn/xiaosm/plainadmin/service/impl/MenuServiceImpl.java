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
    public List<Menu> getByRoleId(Integer roleId) {
        //配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 都要默认值的
        treeNodeConfig.setWeightKey("order");
        treeNodeConfig.setIdKey("id");
        // 最大递归深度
        treeNodeConfig.setDeep(3);
        List<Menu> menus = menuMapper.selectAllByRoleId(roleId);
        if (!menus.isEmpty()) {
            //转换器
            List<Tree<Integer>> treeNodes = TreeUtil.build(menus, 0, treeNodeConfig,
                    (menu, tree) -> {
                        tree.setId(menu.getId());
                        tree.setParentId(menu.getParentMenu());
                        tree.setWeight(menu.getOrder());
                        tree.setName(menu.getName());
                        // 扩展属性 ...
                        tree.putExtra("test", 666);
                        tree.putExtra("other", new Object());
                    });

            List<Menu> menuTree = menus.stream().filter(el -> el.getParentMenu() == 0)
                    .collect(Collectors.toList());
            Long l = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis() - l);

        }
        return menus;
    }

    public List<Menu> buildTree(List<Menu> menuTree, List<Menu> menuList) {
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