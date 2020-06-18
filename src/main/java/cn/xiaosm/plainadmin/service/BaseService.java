/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: BaseService
 * Author:   Young
 * Date:     2020/6/13 15:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service;

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.mapper.RoleMapper;
import cn.xiaosm.plainadmin.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 〈一句话功能简述〉
 * 〈通用 service〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public interface BaseService<DOMAIN> extends IService<DOMAIN> {

    ResponseEntity getById(Integer id);

    ResponseEntity addEntity(DOMAIN domain);

    ResponseEntity deleteEntity(DOMAIN domain);

    ResponseEntity modifyEntity(DOMAIN domain);

    Page<DOMAIN> listOfPage(Page<DOMAIN> page, QueryWrapper<DOMAIN> queryWrapper);

}