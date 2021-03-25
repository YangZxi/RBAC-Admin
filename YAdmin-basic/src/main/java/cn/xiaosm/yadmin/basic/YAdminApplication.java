package cn.xiaosm.yadmin.basic;

import cn.xiaosm.yadmin.basic.util.mail.MailConfig;
import cn.xiaosm.yadmin.basic.service.MenuService;
import cn.xiaosm.yadmin.basic.service.TaskService;
import cn.xiaosm.yadmin.basic.util.CacheUtils;
import cn.xiaosm.yadmin.basic.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;

/**
 * 项目启动类
 *
 * @copyright WWW.XIAOSM.CN
 * @author Young
 */
// @EnableAsync // 启用异步方法调用
// @MapperScan("cn.xiaosm.yadmin.basic.mapper")
// // @PropertySource("classpath:application.properties")
// @EnableConfigurationProperties // 开启配置文件注入到实体类/属性
// @EnableTransactionManagement
// @SpringBootApplication
public class YAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YAdminApplication.class, args);
        loadMenu();
        loadTask();
        loadMail();
    }

    /**
     * 加载菜单
     */
    public static void loadMenu() {
        // 项目启动完成后加载所有菜单
        MenuService menuService = SpringContextUtils.getBean(MenuService.class);
        // List<Menu> list = menuService.list(new QueryWrapper<Menu>().isNotNull("parent_menu"));
        CacheUtils.saveObject("MenuList", menuService.list());
    }

    /**
     * 加载定时任务
     */
    public static void loadTask() {
        // 项目启动完成后加载所有任务
        TaskService taskService = SpringContextUtils.getBean(TaskService.class);
        taskService.startAllTask();
    }

    /**
     * 加载邮件配置
     */
    public static void loadMail() {
        MailConfig mailConfig = SpringContextUtils.getBean(MailConfig.class);
        // MailUtils.sendSimpleMailMessage("yangzx1282@qq.com", "test", "test");
    }
}
