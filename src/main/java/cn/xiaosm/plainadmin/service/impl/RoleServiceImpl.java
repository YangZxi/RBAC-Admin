/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: RoleServiceImpl
 * Author:   Young
 * Date:     2020/6/18 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service.impl;

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.entity.vo.RoleVO;
import cn.xiaosm.plainadmin.entity.vo.UserVO;
import cn.xiaosm.plainadmin.exception.SQLOperateException;
import cn.xiaosm.plainadmin.mapper.RoleMapper;
import cn.xiaosm.plainadmin.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public boolean addEntity(Role role) {
        return false;
    }

    @Override
    public boolean removeEntity(Role role) {
        return false;
    }

    @Override
    public boolean modifyEntity(Role role) {
        role.setUpdateTime(new Date());
        this.updateById(role);
        if (Objects.nonNull( ((RoleVO) role).getMenuIds() )) {
            // 先清除所有的角色信息
            this.removeAllRoleMenu(role.getId());
            // 在进行新的插入
            this.addUserRoles(role.getId(), ((RoleVO) role).getMenuIds());
        }
        return true;
    }

    public void removeAllRoleMenu(Integer userId) {
        roleMapper.deleteAllRoleMenu(userId);
    }

    public int addUserRoles(Integer roleId, Set<Integer> menuIds) {
        for (Integer menuId : menuIds) {
            try {
                int i = roleMapper.insertRoleMenu(roleId, menuId);
            } catch (Exception e) {
                throw new SQLOperateException("保存失败###选择菜单不存在");
            }
        }
        return 1;
    }

    @Override
    public Page<Role> listOfPage(Page<Role> page, QueryWrapper<Role> queryWrapper) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 tota 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        //         // 要点!! 分页返回的对象与传入的对象是同一个
        //         // this.page(page, queryWrapper);
        return roleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Role> getByUserId(Integer userId) {
        return roleMapper.selectByUserId(userId);
    }
}