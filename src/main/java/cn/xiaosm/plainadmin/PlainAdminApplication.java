package cn.xiaosm.plainadmin;

import cn.xiaosm.plainadmin.config.MailConfig;
import cn.xiaosm.plainadmin.service.MenuService;
import cn.xiaosm.plainadmin.service.TaskService;
import cn.xiaosm.plainadmin.utils.CacheUtils;
import cn.xiaosm.plainadmin.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync // 启用异步方法调用
// @MapperScan("cn.xiaosm.plainadmin.mapper")
// @PropertySource("classpath:application.properties")
@EnableConfigurationProperties // 开启配置文件注入到实体类/属性
@EnableTransactionManagement
@SpringBootApplication
public class PlainAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlainAdminApplication.class, args);
        loadMenu();
        loadTask();
        loadMail();
    }

    public static void loadMenu() {
        // 项目启动完成后加载所有菜单
        MenuService menuService = SpringContextUtils.getBean(MenuService.class);
        // List<Menu> list = menuService.list(new QueryWrapper<Menu>().isNotNull("parent_menu"));
        CacheUtils.saveObject("MenuList", menuService.list());
    }

    public static void loadTask() {
        // 项目启动完成后加载所有任务
        TaskService taskService = SpringContextUtils.getBean(TaskService.class);
        taskService.startAllTask();
    }

    public static void loadMail() {
        // 项目启动完成后加载所有任务
        MailConfig mailConfig = SpringContextUtils.getBean(MailConfig.class);
        mailConfig.updateMail();
        // MailUtils.sendSimpleMailMessage("yangzx1282@qq.com", "test", "test");
    }
}
