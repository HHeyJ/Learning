package com.hyq.offer;

/**
 * @author nanke
 * @date 2020/8/17 上午10:21
 *
 * 在看单例模式的实现前我们先来看一下使用单例模式需要注意的四大原则：
 * 1.构造私有。（阻止类被通过常规方法实例化）
 * 2.以静态方法或者枚举返回实例。（保证实例的唯一性）
 * 3.确保实例只有一个，尤其是多线程环境。（确保在创建实例时的线程安全）
 * 4.确保反序列化时不会重新构建对象。（在有序列化反序列化的场景下防止单例被莫名破坏，造成未考虑到的后果）
 */
public class Singleton {

    private Singleton() {
    }

    private static volatile Singleton singleton = null;
    private static Singleton singleton1 = new Singleton();

    /**
     *  1、懒汉式(线程不安全)
     *  顾名思义就是实例在用到的时候才去创建，“比较懒”，用的时候才去检查有没有实例，如果有则返回，没有则新建。
     */
    public static Singleton getSingleton1() {
        return singleton == null ? new Singleton() : singleton;
    }

    /**
     *  2、懒汉式(线程安全)
     *  顾名思义就是实例在用到的时候才去创建，“比较懒”，用的时候才去检查有没有实例，如果有则返回，没有则新建。
     */
    public static Singleton getSingleton2() {
        synchronized (Singleton.class) {
            if (singleton == null) {
                return new Singleton();
            }
        }
        return singleton;
    }

    /**
     *  3、饿汉式(线程安全)
     *  顾名思义就是“比较勤”，实例在初始化的时候就已经建好了，不管你有没有用到，都先建好了再说。好处是没有线程安全的问题，坏处是浪费内存空间。
     */
    public static Singleton getSingleton3() {
        return singleton1;
    }

    /**
     *  4、双重检查(线程安全)
     *  综合了懒汉式和饿汉式两者的优缺点整合而成。看上面代码实现中，特点是在synchronized关键字内外都加了一层 if 条件判断，这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间。
     *  需要注意的是singleton需要加volatile关键字(禁止重排序)
     *  由于JVM指令重排优化的存在。在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设置为默认值。
     *  此时getSingleton4()就可以将singleton对象返回，然而该对象可能还没有初始化。若紧接着另外一个线程来调用getSingleton4()，取到的就是状态不正确的对象，程序就会出错。
     *  singleton = new Singleton() 这句话可以分为三步：
     *      1. 为 singleton 分配内存空间；
     *      2. 初始化 singleton；
     *      3. 将 singleton 指向分配的内存空间。
     *      但是由于JVM具有指令重排的特性，执行顺序有可能变成 1-3-2。 指令重排在单线程下不会出现问题，但是在多线程下会导致一个线程获得一个未初始化的实例。
     *      例如：线程T1执行了1和3，此时T2调用 getSingleton4() 后发现 singleton 不为空，因此返回 singleton， 但是此时的 singleton 还没有被初始化。
     *      使用 volatile 会禁止JVM指令重排，从而保证在多线程下也能正常执行。
     */
    public static Singleton getSingleton4() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    return new Singleton();
                }
            }
        }
        return singleton;
    }

    /**
     *  5、静态内部类(线程安全)
     *  外部类对内部类的引用属于被动引用，不属于必须进行初始化的情况，所以加载类本身并不需要同时加载内部类。
     *  在需要实例化该类是才触发内部类的加载以及本类的实例化，做到了延时加载（懒加载），节约内存。同时因为JVM会保证一个类的<cinit>()方法（初始化方法）执行时的线程安全，从而保证了实例在全局的唯一性。
     */
    public static class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }

    public static Singleton getSingleton5() {
        return SingletonHolder.singleton;
    }

    /**
     * 6、枚举方式(线程安全)
     */
    public enum SingletonEnum {

        INSTANCE
        ;

        private Singleton singleton;

        SingletonEnum() {
            singleton = new Singleton();
        }

        public Singleton getSingleton() {
            return singleton;
        }
    }

    public static Singleton getSingleton6() {
        return SingletonEnum.INSTANCE.getSingleton();
    }


}
