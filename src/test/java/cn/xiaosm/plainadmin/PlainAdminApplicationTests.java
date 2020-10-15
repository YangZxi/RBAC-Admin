package cn.xiaosm.plainadmin;

import cn.xiaosm.plainadmin.entity.Menu;
import cn.xiaosm.plainadmin.entity.User;
import cn.xiaosm.plainadmin.entity.dto.UserDTO;
import cn.xiaosm.plainadmin.mapper.MenuMapper;
import cn.xiaosm.plainadmin.mapper.UserMapper;
import cn.xiaosm.plainadmin.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// @Runwi
@SpringBootTest
class PlainAdminApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void test1() {
        User u = new User();
        u.setUsername("ceshi1");
        u.setPassword("123456");
        User user = userService.login(u);
        System.out.println(user);
    }
    
    @Test
    public void testSave() {
        User user = new User();
        for (int i = 0; i < 100; i++) {
            user.setUsername("ceshi-" + i);
            user.setNickname("测试用户-" + i);
            user.setPassword("21232f297a57a5a743894a0e4a801fc3");
            boolean responseEntity = userService.addEntity(user);
            try {
                System.out.println(new ObjectMapper().writeValueAsString(responseEntity));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 查询用户和用户角色
     */
    @Test
    public void testFindUserAndRole() throws JsonProcessingException {
        User ex = new User();
        ex.setUsername("ceshi");
        ex.setPassword("123456");
        UserDTO userDTO = userMapper.selectByUsernameAndPassword(ex);
        System.out.println(new ObjectMapper()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .writeValueAsString(userDTO));

        List<Menu> menus = menuMapper.selectAllByRoleIdOfTree(Integer.toString(2));
        System.out.println(new ObjectMapper()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .writeValueAsString(menus));

    }

}
