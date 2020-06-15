/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: BaseService
 * Author:   Young
 * Date:     2020/6/13 15:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.service;

import cn.xiaosm.plainadmin.entity.ResponseEntity;

/**
 * 〈一句话功能简述〉
 * 〈通用 service〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public interface BaseService<DOMAIN>  {

    ResponseEntity findById(Integer id);

    ResponseEntity saveEntity(DOMAIN domain);

    ResponseEntity removeEntity(Integer id);

    ResponseEntity modifyEntity(DOMAIN domain);

}