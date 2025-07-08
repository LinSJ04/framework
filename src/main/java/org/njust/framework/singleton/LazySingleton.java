package org.njust.framework.singleton;

/**
 * 懒汉式单例
 * 需要使用单例对象时，才创建实例
 */
public class LazySingleton {
    // volatile 修改可见性
    private static volatile LazySingleton instance;

    // 私有构造函数
    private LazySingleton() {

    }

    // 全局访问点
    public static LazySingleton getInstance() {
        // 双重检查锁
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
