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
package cn.xiaosm.yadmin.basic.service;

import cn.xiaosm.yadmin.basic.entity.ResponseBody;
import cn.xiaosm.yadmin.basic.entity.vo.Pager;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 〈一句话功能简述〉
 * 〈通用 service〉
 *
 * @author Young
 * @create 2020/6/13
 * @since 1.0.0
 */
public interface BaseService<DOMAIN> extends IService<DOMAIN> {

    ResponseBody getById(Integer id);

    boolean addEntity(DOMAIN domain);

    boolean removeEntity(DOMAIN domain);

    boolean modifyEntity(DOMAIN domain);

    // default <E extends DOMAIN> Page<DOMAIN> listOfPage(Page<DOMAIN> page, E e) {
    //     return page;
    // };

    Pager<DOMAIN> listOfPage(Pager<DOMAIN> Pager, QueryWrapper<DOMAIN> wrapper);

}