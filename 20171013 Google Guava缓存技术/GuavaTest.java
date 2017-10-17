import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.cache.Weigher;

import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by luojian on 2017/10/10.
 *
 * 1,缓存最大容量管理(缓存数 & 权重)  &&  缓存移除监听
 * 2,超时失效(顺序取Key值)  &&  缓存移除监听
 * 3,缓存命中率(随机数取Key值)
 *
 */
public class GuavaTest {

    //LoadingCache在缓存项不存在时可以自动加载缓存
    //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
    public static final LoadingCache<Integer, Employee> cache = CacheBuilder.newBuilder()

            .concurrencyLevel(2)  // 设置并发级别，并发级别是指可以同时 写缓存 的线程数(默认值4)

            .initialCapacity(10)  // 设置缓存容器的初始容量

            .maximumSize(8)   // 设置缓存最大容量，超过之后就会按照LRU(内存管理的一种页面置换算法)最近最少使用算法来移除缓存项

//            .maximumWeight(10)  //权重（基于缓存条目个数和总权重是互斥的，只能二选一）
//            .weigher(new Weigher<Integer, Employee>() {
//                @Override
//                public int weigh(Integer key, Employee employee) {
//                    if (employee.getId() % 2 == 0) {
//                        return 20;
//                    } else {
//                        return 5;
//                    }
//                }
//            })


            .refreshAfterWrite(30, TimeUnit.SECONDS)  //在这个时间段内没有被写访问，就会重新加载缓存

//            .expireAfterWrite(5, TimeUnit.HOURS)  //在这个时间段内没有被写访问，就会被回收
//
//            .expireAfterAccess(5, TimeUnit.DAYS)   //在这个时间段内没有被读/写访问，就会被回收

//            .weakKeys()    //弱引用的键 -允许垃圾回收
//            .weakValues()  //弱引用的值 -允许垃圾回收(默认使用强引用)

            .recordStats()   // 记录统计缓存的命中率

            // 缓存移除监听器
            .removalListener(new RemovalListener<Object, Object>() {
                public void onRemoval(RemovalNotification<Object, Object> notification) {
                    //To do something here
                    System.out.println("Key " + notification.getKey() + " was removed, cause is " + notification.getCause());
                }
            })

            // build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build(new CacheLoader<Integer, Employee>() {
                @Override
                public Employee load(Integer key) throws Exception {
                    System.out.println("加载缓存employee......" + key);
                    GuavaTest.Employee employee = new GuavaTest.Employee();
                    employee.setId(key);
                    employee.setName("name " + key);
                    return employee;
                }
            });

    public static void main(String[] args) {
        try {
            //1，缓存命中率测试  &&  缓存最大容量测试****************
            for (int i = 1; i <= 10; i++) {
                // 从缓存中获取数据，如果没有则通过CacheLoader加载缓存数据
//                GuavaTest.Employee employee = cache.get(i);
                double randomNum = Math.floor(Math.random()*10 + 1);
                GuavaTest.Employee employee = cache.get( (new Double(randomNum)).intValue());
//                cache.invalidateAll();
                System.out.println(">>> "+employee.getName());
                // 休眠1秒
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("\n缓存的命中情况: " + cache.stats().toString());
            System.out.println("命中率: "+cache.stats().hitRate());  //命中率
            System.out.println("回收数: "+cache.stats().evictionCount());  //缓存项被回收的总数
            System.out.println("平均加载时间: "+cache.stats().averageLoadPenalty());  //加载新值的平均时间，单位为纳秒





/*
            //2,常用的操作方法********************************

            //根据key重新获取值（原来的值会一直等到新值回来才会被替换，在获取新值的过程如果出现异常，原来的值不会被丢弃。）
            cache.refresh(1);

            //向缓存中添加一个或者多个缓存数据
            cache.put(12,new Employee());
            cache.putAll(new HashMap<Integer, Employee>());

            //从缓存中移除缓存数据
            cache.invalidate(1);
            cache.invalidateAll();

            //清空缓存
            cache.cleanUp();

            //缓存数量
            cache.size();

            //获得缓存数据的ConcurrentMap<K, V>快照
            ConcurrentMap<Integer, Employee> concurrentMap = cache.asMap();
*/



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Employee {
        private int id;
        public String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return id + "| " + name;
        }
    }

}
