/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: RoleMenuDTO
 * Author:   Young
 * Date:     2020/6/14 14:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity.dto;

import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.Role;
import lombok.Data;

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
public class RoleDTO extends Role {

    private List<Menu> menus;

}