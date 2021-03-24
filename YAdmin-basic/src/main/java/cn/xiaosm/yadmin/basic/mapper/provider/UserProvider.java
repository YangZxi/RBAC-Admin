/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserProvider
 * Author:   Young
 * Date:     2020/6/14 13:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.basic.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
public class UserProvider {

    public String sqlFindUserTrack(Map map) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("user_login_track");
        sql.WHERE("user_id = #{userId}");
        sql.ORDER_BY("login_time DESC");
        if (Objects.nonNull(map.get("length"))) {
            sql.LIMIT("#{length}");
        } else {
            sql.LIMIT(2);
        }
        return sql.toString();
    }

}