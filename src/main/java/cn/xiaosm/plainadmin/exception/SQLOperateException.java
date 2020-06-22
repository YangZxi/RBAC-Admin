/**
 * Copyright: 2019-2020
 * FileName: FieldUniqueException
 * Author:   Young
 * Date:     2020/6/20 17:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.exception;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/20
 * @since 1.0.0
 */
public class SQLOperateException extends RuntimeException {

    public SQLOperateException(String message) {
        super(message);
    }

}