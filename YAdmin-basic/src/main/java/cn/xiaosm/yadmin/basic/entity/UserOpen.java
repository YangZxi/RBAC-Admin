package cn.xiaosm.yadmin.basic.entity;

import cn.xiaosm.yadmin.basic.entity.enums.UserOpenType;
import cn.xiaosm.yadmin.basic.entity.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 第三方用户绑定信息
 *
 * @author Young
 * @create 2021/4/16
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class UserOpen extends BaseEntity{

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private String openId;
    private UserOpenType type;
    private String nickname;
    private String avatar;
    private StatusEnum status;

}