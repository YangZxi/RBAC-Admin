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
import cn.xiaosm.plainadmin.mapper.RoleMapper;
import cn.xiaosm.plainadmin.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity addEntity(Role role) {
        return null;
    }

    @Override
    public ResponseEntity deleteEntity(Role role) {
        return null;
    }

    @Override
    public ResponseEntity modifyEntity(Role role) {
        return null;
    }

    @Override
    public Page<Role> listOfPage(Page<Role> page, QueryWrapper<Role> queryWrapper) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        // this.page(page, queryWrapper);
        return roleMapper.selectPage(page, queryWrapper);
    }
}