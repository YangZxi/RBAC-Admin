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
    @Select("SELECT u.* " +
            " FROM `user` u " +
            " WHERE u.username = #{username} AND u.password = #{password}")
    @Results(id = "userRoleMap", value = {
            // 不配置id 无法注入进id字段
            @Result(id = true, property = "id", column = "id"),
            // @Result(id = true, property = "role.id", column = "r.id"),
            // @Result(property = "role.name", column = "r.name"),
            // @Result(property = "role.nameZh", column = "r.name_zh"),
            // @Result(property = "role.desc", column = "r.desc"),
            // @Result(property = "role.status", column = "r.status"),
            @Result(property = "roles", column = "id",
                    many = @Many(select = "cn.xiaosm.plainadmin.mapper.RoleMapper.selectByUserIdForName")),
            @Result(property = "userLoginTracks", column = "id",
                    many = @Many(select = "cn.xiaosm.plainadmin.mapper.UserMapper.selectUserTrackByUserId"))
    })
    UserDTO selectByUsernameAndPassword(User user);

    @Select("SELECT u.* " +
            " FROM `user` u " +
            " WHERE u.username = #{username}")
    @ResultMap(value = "userRoleMap")
    UserDTO selectByUsername(String username);

    /**
     * 查询用户最近 1 条登录记录
     * @param userId 用户id
     * @return
     */
    @Select("SELECT * FROM user_login_track WHERE user_id = #{userId} ORDER BY login_time DESC LIMIT 1")
    // @SelectProvider(value = UserProvider.class, method = "sqlFindUserTrack")
    UserLoginTrack selectUserTrackByUserId(@Param("userId") Integer userId);
}