/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: User
 * Author:   Young
 * Date:     2020/6/13 14:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@TableName(value = "user")
public class User extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String gender;
    private Integer age;
    private Integer status;
    private String roleIds;
    @JsonIgnore
    private String uuid;

    public User(Integer id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? "" : uuid;
    }
}