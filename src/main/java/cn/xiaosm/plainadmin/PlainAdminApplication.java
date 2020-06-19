package cn.xiaosm.plainadmin;

import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.utils.MemoryUtils;
import cn.xiaosm.plainadmin.utils.SpringContextUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@MapperScan("cn.xiaosm.plainadmin.mapper")
@SpringBootApplication
public class PlainAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlainAdminApplication.class, args);
        load();
    }

    public static void load() {
        MenuService menuService = SpringContextUtils.getBean(MenuService.class);
        // List<Menu> list = menuService.list(new QueryWrapper<Menu>().isNotNull("parent_menu"));
        MemoryUtils.saveObject("MenuList", menuService.list());
    }

}
