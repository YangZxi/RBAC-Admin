/**
 * Copyright: 2019-2020
 * FileName: ExcelVO
 * Author:   Young
 * Date:     2020/12/1 20:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.yadmin.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/12/1
 * @since 1.0.0
 */
@Data
public class ExcelVO {

    private String name;
    private List<Column> columns;
    private String data;

    @Data
    public static class Column {
        private String key;
        private String value;
    }
}