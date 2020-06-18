/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserService
 * Author:   Young
 * Date:     2020/6/13 14:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service;

import cn.xiaosm.plainadmin.entity.ResponseEntity;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.dto.UserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public interface UserService extends IService<User>, BaseService<User> {

    ResponseEntity getById(Integer id);

    UserDTO getByUsername(String username);

    User login(User user);
}