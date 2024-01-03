package com.sb.common.generic;

/*
利用枚举创建单例模式。 线程安全（推荐使用）
*/
public class Singleton {

    private enum SingletonEnum {
        INSTANCE;
        private final Singleton instance;

        SingletonEnum() {
            instance = new Singleton();
        }

        private Singleton getInstance() {
            return instance;
        }
    }

    public static Singleton getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }
}
