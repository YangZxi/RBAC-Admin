package cn.xiaosm.yadmin.basic.util.cache;

import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.xiaosm.yadmin.basic.util.SpringContextUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉
 * 〈〉
 *
 * @author Young
 * @create 2020/6/16
 * @since 1.0.0
 */
public class CacheUtils {

    static {
        // 判断是否使用了redis缓存
        boolean b = ClassLoaderUtil.isPresent("org.springframework.data.redis.core.RedisTemplate");
        if (b) {
            handler = (CacheHandler) SpringContextUtils.createClass("cn.xiaosm.yadmin.basic.util.cache.RedisCache");
        } else {
            // 未使用则用java来进行存储对象
            handler = new JavaCache();
        }
    }

    // 缓存处理器
    private static CacheHandler handler;


    public static void saveObject(String key, Object value) {
        saveObject(key, value, 0);
    }

    /**
     * 保存对象
     * @param key
     * @param value
     * @param exp 到期时间 单位 秒
     */
    public static void saveObject(String key, Object value, long exp) {
        handler.set(key, value, exp);
    }

    /**
     * 获取对象
     * @param key
     * @return
     */
    public static Object getObject(String key) {
        return handler.get(key);
    }

    /**
     * 删除对象
     * @param key
     */
    public static void removeObject(String key) {
        handler.delete(key);
    }

}