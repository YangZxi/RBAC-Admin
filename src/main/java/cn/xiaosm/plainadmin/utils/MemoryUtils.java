/**
 * Copyright: 2019-2020，小树苗(www.xiaosm.cn)
 * FileName: MemoryUtils
 * Author:   Young
 * Date:     2020/6/16 21:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * Young         修改时间           版本号             描述
 */
package cn.xiaosm.plainadmin.utils;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
public class MemoryUtils {

    private static Map<String, Object> map = new ConcurrentHashMap<>();

    public static void saveObject(String key, Object o) {
        map.put(key, o);
    }

    public static Object getObject(String key) {
        return map.get(key);
    }

    public static<T> Object getObject(String key, Class clazz) {
        if (map.containsKey(key)) {
            try {
                return new ObjectMapper().readValue(JSONUtil.toJsonStr(map.get(key)), clazz);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {

        }
        return null;
    }

    public static void removeObject(String key) {
        map.remove(key);
    }

}