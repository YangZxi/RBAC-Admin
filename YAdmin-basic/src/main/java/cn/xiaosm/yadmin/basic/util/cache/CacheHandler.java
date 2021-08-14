package cn.xiaosm.yadmin.basic.util.cache;

/**
 * @author Young
 * @create 2021/5/8
 * @since 1.0.0
 */
public interface CacheHandler {

    void set(String key, Object value, long exp);

    Object get(String key);

    void delete(String key);

}