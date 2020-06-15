/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserMapper
 * Author:   Young
 * Date:     2020/6/13 14:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.mapper;

import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.UserLoginTrack;
import cn.xiaosm.plainadmin.entity.dto.UserDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户登录查询的 sql
     * 返回数据包括用户基本信息、所对应的角色信息和最近1条登录记录
     * @param user
     * @return UserDTO
     */
    @Select("SELECT u.*,r.id AS `r.id`, r.name AS `r.name`, r.decs AS `r.desc`, r.status AS `r.status`" +
            " FROM `user` u LEFT JOIN `role` r ON r.id = u.role_id" +
            " WHERE u.username = #{username} AND u.password = #{password}")
    @Results(id = "userRoleMap", value = {
            // 不配置id 无法反射进对象
            @Result(id = true, property = "id", column = "id"),
            @Result(id = true, property = "role.id", column = "r.id"),
            @Result(column = "r.name", property = "role.name"),
            @Result(column = "r.desc", property = "role.desc"),
            @Result(column = "r.status", property = "role.status"),
            @Result(property = "menus", column = "r.id",
                    many = @Many(select = "cn.xiaosm.plainadmin.mapper.MenuMapper.findAllByRoleId")),
            @Result(property = "userLoginTracks", column = "id",
                    many = @Many(select = "cn.xiaosm.plainadmin.mapper.UserMapper.findUserTrackByUserId"))
    })
    UserDTO findByUsernameAndPassword(User user);

    /**
     * 查询用户最近 2 条登录记录
     * @param userId 用户id
     * @return
     */
    @Select("SELECT * FROM user_login_track WHERE user_id = #{userId} ORDER BY login_time DESC LIMIT 1")
    // @SelectProvider(value = UserProvider.class, method = "sqlFindUserTrack")
    UserLoginTrack findUserTrackByUserId(@Param("userId") Integer userId);
}