/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MenuProvider
 * Author:   Young
 * Date:     2020/6/14 17:42
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.mapper.provider;

import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
public class MenuProvider {

    /**
     * 通过 角色 id 查询菜单
     * 设定父级菜单为 1 (查询根目录菜单)
     * @param roleId
     * @return
     */
    public String sqlSelectByRoleIdAndRootMenu(String roleIds) {
        return this.sqlSelectByRoleIdAndParentId(roleIds, 1);
    }

    /**
     * 通过角色 id 查询所有的菜单
     * @param roleIds
     * @return
     */
    public String sqlSelectByRoleIds(String roleIds) {
        return this.sqlSelectByRoleIdAndParentId(roleIds, null);
    }

    /**
     * 通过角色 id 和父级菜单 id 查询菜单
     * @param roleIds
     * @param parentId
     * @return
     */
    public String sqlSelectByRoleIdAndParentId(String roleIds, Integer parentId) {
        if (StrUtil.isBlank(roleIds)) roleIds = "";
        SQL sql = new SQL();
        sql.SELECT("m.*")
            .FROM("menu m")
            .LEFT_OUTER_JOIN("role_menu rm ON rm.menu_id = m.id")
            .LEFT_OUTER_JOIN("role r ON rm.role_id = r.id")
            .WHERE("r.id IN (${roleIds})")
            .WHERE("m.status = 1");
        if (Objects.isNull(parentId)) {
            sql.WHERE("NOT ISNULL(parent_menu_id)");
        } else if (parentId == 1) {
            // 由于调用此方法的 Mapper 函数只有(给)了一个参数，
            // 所以如果使用占位符代替会出现值和第一个参数值相同
            sql.WHERE("m.parent_menu_id = 1");
        } else {
            sql.WHERE("m.parent_menu_id = #{parentId}");
        }
        sql.GROUP_BY("m.id");
        return sql.toString();
    }

}