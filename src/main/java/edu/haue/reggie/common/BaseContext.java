package edu.haue.reggie.common;

/**
 * 封装ThreadLocal的工具类，用于用户id的保存和获取，因为在MetaObjectHandler中无法得到HttpServletRequest对象
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

}
