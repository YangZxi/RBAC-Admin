/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MenuVO
 * Author:   Young
 * Date:     2020/6/19 16:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.entity.vo;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

import java.util.Objects;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/19
 * @since 1.0.0
 */
@Data
public class MenuVO {

    private Integer id;
    private Integer type;
    private String name;
    private int parentMenu;
    private String status;
    private String showType; // 数据展示格式默认为 链表

    public void setParentMenu(int parentMenu) {
        this.parentMenu = parentMenu < 0 ? 0 : parentMenu;
    }

    public void setShowType(String showType) {
        this.showType = StringUtils.isBlank(showType) ? "list" : showType;
    }
}