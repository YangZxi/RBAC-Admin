/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: OperatorServiceImpl
 * Author:   Young
 * Date:     2020/6/16 10:33
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service.impl;

import cn.xiaosm.plainadmin.entity.OperatorLog;
import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.mapper.OperatorMapper;
import cn.xiaosm.plainadmin.mapper.UserMapper;
import cn.xiaosm.plainadmin.service.OperatorService;
import cn.xiaosm.plainadmin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, OperatorLog> implements OperatorService {

    @Override
    public ResponseEntity getById(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity addEntity(OperatorLog operatorLog) {
        return null;
    }

    @Override
    public ResponseEntity deleteEntity(OperatorLog operatorLog) {
        return null;
    }

    @Override
    public ResponseEntity modifyEntity(OperatorLog operatorLog) {
        return null;
    }
}