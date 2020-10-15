/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: PropServiceImpl
 * Author:   Young
 * Date:     2020/6/18 16:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service.impl;

import cn.xiaosm.plainadmin.entity.Prop;
import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.mapper.PropMapper;
import cn.xiaosm.plainadmin.service.PropService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/18
 * @since 1.0.0
 */
@Service
public class PropServiceImpl extends ServiceImpl<PropMapper, Prop> implements PropService {

    @Autowired
    PropMapper propMapper;

    @Override
    public ResponseBody getById(Integer id) {
        return null;
    }

    @Override
    public boolean addEntity(Prop prop) {
        return false;
    }

    @Override
    public boolean removeEntity(Prop prop) {
        return false;
    }

    @Override
    public boolean modifyEntity(Prop prop) {
        boolean update = this.update(prop, new UpdateWrapper<Prop>()
                .eq("id", prop.getId())
                .eq("prop_key", prop.getPropKey()));
        // 如果更新没有成功则进行插入操作
        if (!update) {
            prop.setId(null);
            update = this.save(prop);
        }
        return update;
    }

    @Override
    public Page<Prop> listOfPage(Page<Prop> page, QueryWrapper<Prop> queryWrapper) {
        return null;
    }
}