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

import cn.xiaosm.plainadmin.entity.ResponseBody;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.UserLoginTrack;
import cn.xiaosm.plainadmin.entity.dto.UserDTO;

import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public interface UserService extends BaseService<User> {

    ResponseBody getById(Integer id);

    int removeById(Integer id);

    int removeByIds(Set<Integer> ids);

    UserDTO getByUsername(String username);

    UserDTO getByUsername(String openId, String source);

    User login(User user);

    List<UserLoginTrack> listOfTrack(Integer userId, Integer size);

    boolean addLoginTrack(Integer userId, String ip);
}