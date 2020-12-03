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

import java.util.Date;
import java.util.List;

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
            // 查询角色信息
            @Result(property = "roles", column = "id",
                    many = @Many(select = "cn.xiaosm.plainadmin.mapper.RoleMapper.selectIdAndNameByUserId")),
            // 查询用户登录足迹
            @Result(property = "userLoginTracks", column = "id",
                    many = @Many(select = "cn.xiaosm.plainadmin.mapper.UserMapper.selectUserTrackByUserId"))
    })
    UserDTO selectByUsernameAndPassword(User user);

    @Select("SELECT u.* " +
            " FROM `user` u " +
            " WHERE u.username = #{username}")
    @ResultMap(value = "userRoleMap")
    UserDTO selectByUsername(String username);

    @Select("SELECT u.* " +
            " FROM `user` u " +
            " WHERE u.${source} = #{openId}")
    @ResultMap(value = "userRoleMap")
    UserDTO selectByOpenId(@Param("openId") String openId,@Param("source") String source);

    /**
     * 查询用户最近 1 条登录记录
     * @param userId 用户id
     * @return
     */
    @Select("SELECT * FROM user_login_track WHERE user_id = #{userId} ORDER BY login_time DESC LIMIT 1")
    // @SelectProvider(value = UserProvider.class, method = "sqlFindUserTrack")
    UserLoginTrack selectUserTrackByUserId(@Param("userId") Integer userId);

    @Update("UPDATE `user` SET `status` = 2 WHERE `id` = #{userId}")
    int updateUserIsDeleted(Integer userId);

    /**
     * 删除所有的用户角色信息
     * @param userId
     * @return
     */
    @Delete("DELETE FROM `user_role` WHERE `user_id` = #{userId}")
    int deleteUserRole(Integer userId);

    /**
     * 插入用户角色信息
     * @param userId
     * @param roleId
     * @return
     */
    @Insert("INSERT INTO `user_role`(`user_id`, `role_id`) VALUES (#{userId}, #{roleId})")
    int insertUserRole(Integer userId, Integer roleId);

    @Select("SELECT * FROM `user_login_track` WHERE `user_id` = #{userId} ORDER BY `login_time` DESC LIMIT 0,#{size}")
    List<UserLoginTrack> selectUserTrack(Integer userId, Integer size);

    @Insert("INSERT INTO `user_login_track`(`user_id`, `login_ip`, `login_time`) VALUES (#{userId}, #{loginIp}, #{loginTime})")
    int insertUserTrack(UserLoginTrack track);

    // @Insert("INSERT INTO `user_login_track`(`user_id`, `login_ip`, `login_time`) VALUES (#{userId}, #{ip}, #{date})")
    // int insertUserTrack(Integer userId, String ip, String date);
}