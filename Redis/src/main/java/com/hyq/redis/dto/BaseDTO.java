package com.hyq.redis.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nanke
 * @date 2020/3/21 下午5:35
 */
public class BaseDTO {

    public static List<String> conver(Class clazz) {

        return Stream.of(clazz.getDeclaredFields())
                .map(Field::getName).collect(Collectors.toList());
    }

    /**
     * 对象 - HashMap转换
     * @param object
     * @return
     */
    public static HashMap<String,Object> conver(Object object) {

        HashMap<String,Object> resultMap = new HashMap<>();
        Stream.of(object.getClass().getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                resultMap.put(field.getName(),field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return resultMap;
    }

    /**
     * HashMap - 对象转换
     * @param propertiesMap
     * @param clazz
     * @return
     */
    public static Object conver(Map<String,Object> propertiesMap, Class clazz) {

        try {
            Object o = clazz.newInstance();
            for (Map.Entry<String, Object> entry : propertiesMap.entrySet()) {
                Field field = clazz.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(o,entry.getValue());
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
