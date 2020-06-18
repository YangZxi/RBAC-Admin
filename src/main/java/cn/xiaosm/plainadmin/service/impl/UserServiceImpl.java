/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserServiceImpl
 * Author:   Young
 * Date:     2020/6/13 14:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service.impl;

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.dto.UserDTO;
import cn.xiaosm.plainadmin.mapper.UserMapper;
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.ResponseUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity deleteEntity(User user) {
        return null;
    }

    @Override
    public ResponseEntity modifyEntity(User o) {
        return null;
    }

    @Override
    public ResponseEntity addEntity(User o) {
        // 加密密码
        o.setPassword(passwordEncoder.encode(o.getPassword()));
        boolean b = this.save(o);
        return b ? ResponseUtils.buildSuccess(o) : ResponseUtils.buildFail("保存失败");
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Override
    public UserDTO getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Deprecated
    public User login(User user) {
        User condition = new User();
        condition.setUsername(user.getUsername());
        condition.setPassword(passwordEncoder.encode(user.getPassword()));
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(condition);
        User re = this.getOne(queryWrapper);
        if (Objects.isNull(re)) return null;
        return re;
    }

    public List findByCondition(QueryWrapper<User> queryWrapper) {
        List<User> list = this.list(queryWrapper);
        return list;
    }

    @Override
    public Page<User> listOfPage(Page<User> page, QueryWrapper<User> queryWrapper) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return userMapper.selectPage(page, queryWrapper);
    }

}