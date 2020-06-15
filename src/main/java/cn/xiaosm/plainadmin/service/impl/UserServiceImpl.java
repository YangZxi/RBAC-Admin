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
import cn.xiaosm.plainadmin.mapper.UserMapper;
import cn.xiaosm.plainadmin.service.UserService;
import cn.xiaosm.plainadmin.utils.Response;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public ResponseEntity findById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity removeEntity(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity modifyEntity(User o) {
        return null;
    }

    @Override
    public ResponseEntity saveEntity(User o) {
        boolean b = this.save(o);
        return b ? Response.buildSuccess(o) : Response.buildFail("保存失败");
    }

    @Override
    public User login(User user) {
        User condition = new User();
        condition.setUsername(user.getUsername());
        condition.setPassword(user.getPassword());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(condition);
        List<User> list = this.list(queryWrapper);
        if (list.isEmpty()) return null;
        return list.get(0);
    }

    public List findByCondition(QueryWrapper<User> queryWrapper) {
        List<User> list = this.list(queryWrapper);
        return list;
    }

}