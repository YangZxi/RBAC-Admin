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
package cn.xiaosm.plainadmin.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

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
     * 设定父级菜单为 0
     * @param roleId
     * @return
     */
    public String selectByRoleId(Integer roleId) {
        return this.selectByRoleIdAndParentId(roleId, 0);
        // SQL sql = new SQL();
        // sql.SELECT("r.id as `r.id`, m.*")
        //         .FROM("role_menu rm")
        //         .LEFT_OUTER_JOIN("role r ON rm.role_id = r.id")
        //         .LEFT_OUTER_JOIN("menu m ON rm.menu_id = m.id")
        //         .WHERE("r.id = #{roleId}");
        // return sql.toString();
    }

    /**
     * 通过角色 id 和父级菜单 id 查询菜单
     * @param roleId
     * @param parentId
     * @return
     */
    public String selectByRoleIdAndParentId(Integer roleId, Integer parentId) {
        SQL sql = new SQL();
        sql.SELECT("r.id as `r.id`, m.*")
            .FROM("role_menu rm")
            .LEFT_OUTER_JOIN("role r ON rm.role_id = r.id")
            .LEFT_OUTER_JOIN("menu m ON rm.menu_id = m.id")
            .WHERE("r.id = #{roleId}");
        if (parentId == 0) {
            // 由于调用此方法的函数只有一个参数，
            // 所以如果使用占位符代替会出现值和第一个参数值相同
            sql.WHERE("m.parent_menu = 0");
        } else {
            sql.WHERE("m.parent_menu = #{parentId}");
        }
        return sql.toString();
    }

}