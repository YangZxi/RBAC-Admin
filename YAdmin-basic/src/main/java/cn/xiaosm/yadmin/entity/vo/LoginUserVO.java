/**
 * Copyright: 2019-2020
 * FileName: LoginUserVO
 * Author:   Young
 * Date:     2020/6/27 14:26
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/21
 * @since 1.0.0
 */
@Data
public class LoginUserVO {

    private String username;
    private String password;

}