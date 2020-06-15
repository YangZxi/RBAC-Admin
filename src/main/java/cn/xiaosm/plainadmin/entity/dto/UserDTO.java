/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserDTO
 * Author:   Young
 * Date:     2020/6/14 9:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity.dto;

import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.UserLoginTrack;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
@Data
public class UserDTO extends User implements Serializable {

    private Role role;
    private List<Menu> menus;
    private List<UserLoginTrack> userLoginTracks;

}