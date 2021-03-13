package cn.xiaosm.yadmin;

import cn.xiaosm.yadmin.config.MailConfig;
import cn.xiaosm.yadmin.service.MenuService;
import cn.xiaosm.yadmin.service.TaskService;
import cn.xiaosm.yadmin.util.CacheUtils;
import cn.xiaosm.yadmin.util.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 *
 * @copyright WWW.XIAOSM.CN
 * @author Young
 */
@EnableAsync // 启用异步方法调用
@MapperScan("cn.xiaosm.yadmin.mapper")
// @PropertySource("classpath:application.properties")
@EnableConfigurationProperties // 开启配置文件注入到实体类/属性
@EnableTransactionManagement
@SpringBootApplication
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
        mailConfig.updateMail();
        // MailUtils.sendSimpleMailMessage("yangzx1282@qq.com", "test", "test");
    }
}
