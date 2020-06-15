/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: UserLoginTrack
 * Author:   Young
 * Date:     2020/6/14 10:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/14
 * @since 1.0.0
 */
@Data
@TableName(value = "user_login_track")
public class UserLoginTrack implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String loginIp;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date loginTime;

}