package cn.xiaosm.yadmin.basic.service.impl;

import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.User;
import cn.xiaosm.yadmin.basic.entity.UserLoginTrack;
import cn.xiaosm.yadmin.basic.entity.dto.UserDTO;
import cn.xiaosm.yadmin.basic.entity.vo.UserVO;
import cn.xiaosm.yadmin.basic.exception.SQLOperateException;
import cn.xiaosm.yadmin.basic.mapper.UserMapper;
import cn.xiaosm.yadmin.basic.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
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
    public ResponseBody getById(Integer id) {
        return null;
    }

    @Override
    public boolean removeEntity(User user) {
        return false;
    }

    @Override
    @Transactional
    public boolean modifyEntity(User user) {
        if (Objects.nonNull(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setUpdateTime(new Date());
        userMapper.updateById(user);
        if (Objects.nonNull( ((UserVO) user).getRoleIds() )) {
            // 先清除所有的角色信息
            this.removeUserRoles(user.getId());
            // 在进行新的插入
            this.addUserRoles(user.getId(), ((UserVO) user).getRoleIds());
        }
        return true;
    }

    @Override
    @Transactional
    public boolean addEntity(User user) {
        user.setCreateTime(new Date()).setUpdateTime(new Date());
        // 设置默认密码
        user.setPassword(passwordEncoder.encode("123456"));
        userMapper.insert(user);
        this.addUserRoles(user.getId(), ((UserVO) user).getRoleIds());
        return true;
    }

    /**
     * 删除用户的所有角色信息
     * @param userId
     */
    public void removeUserRoles(Integer userId) {
        userMapper.deleteUserRole(userId);
    }

    /**
     * 添加用户的角色
     * @param userId
     * @param roleIds
     * @return
     */
    public int addUserRoles(Integer userId, Set<Integer> roleIds) {
        int i = 0;
        for (Integer roleId : roleIds) {
            try {
                i = userMapper.insertUserRole(userId, roleId);
            } catch (Exception e) {
                throw new SQLOperateException("保存失败###选择角色不存在");
            }
        }
        return i;
    }

    @Override
    public int removeById(Integer id) {
        return userMapper.updateUserStatusToDeleted(id);
    }

    @Override
    public int removeByIds(Set<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            if (id == 1) {
                throw new SQLOperateException("系统保留数据，请勿操作");
            }
            count += this.removeById(id);
        }
        return count;
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

    /**
     * 通过快捷方式登录
     * @param openId
     * @param source
     * @return
     */
    @Override
    public UserDTO getByUsername(String openId, String source) {
        return userMapper.selectByOpenId(openId, source);
    }

    /**
     * 登录方法
     *
     * 方法已废弃，真实的登录应使用 UserDetailsServiceImpl.loadUserByUsername
     *
     * @param user
     * @return
     */
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

    @Override
    public List<UserLoginTrack> listOfTrack(Integer userId, Integer size) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return userMapper.selectUserTrack(userId, size);
    }

    @Override
    public boolean addLoginTrack(Integer userId, String ip) {
        UserLoginTrack track = new UserLoginTrack();
        track.setUserId(userId);
        track.setLoginIp(ip);
        track.setLoginTime(new Date());
        return userMapper.insertUserTrack(track) == 1 ? true : false;
        // return userMapper.insertUserTrack(userId, ip, new Date()) == 1 ? true : false;
    }
}