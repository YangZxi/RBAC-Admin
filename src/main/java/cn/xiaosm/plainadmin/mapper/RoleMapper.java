/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: RoleMapper
 * Author:   Young
 * Date:     2020/6/14 10:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.mapper;

import cn.xiaosm.plainadmin.entity.Role;
import cn.xiaosm.plainadmin.entity.dto.RoleDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户 id 查询角色信息
     * @param userId
     * @return
     */
    @Select("SELECT r.* FROM role r " +
            " LEFT JOIN user_role ur ON ur.role_id = r.id" +
            " LEFT JOIN `user` u ON ur.user_id = u.id" +
            " WHERE u.id = #{userId}")
    List<Role> selectByUserId(Integer userId);

    @Select("SELECT r.id, r.name FROM role r " +
            " LEFT JOIN user_role ur ON ur.role_id = r.id" +
            " LEFT JOIN `user` u ON ur.user_id = u.id" +
            " WHERE u.id = #{userId}")
    List<Role> selectByUserIdForName(Integer userId);

    @Select("SELECT * FROM `role` WHERE `role`.user_id = #{userId}")
    // @Results(id = "menus", value = {
    //         @Result(property = "")
    // })
    RoleDTO findIncludeMenuByUserId(Integer userId);
}