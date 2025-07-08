package org.njust.framework.singleton;

/**
 * 饿汉式单例
 * 一开始就创建对象
 */
public class EagerSingleton {
    // final 不可变
    private static final EagerSingleton instance = new EagerSingleton();

    // 私有构造函数
    private EagerSingleton(){}

    // 全局访问点
    public static EagerSingleton getInstance(){
        return instance;
    }
}
