import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by luojian on 2017/10/10.
 * <p>
 * 1,测试Guava并发访问场景
 * 2,不同的键key1 key2 key3 不会加锁
 */
public class TestGuava<K, V> {
    private Cache<K, V> cache = CacheBuilder.newBuilder().maximumSize(22).expireAfterWrite(10, TimeUnit.MINUTES).build();

    public Object getCache(K keyValue, final String ThreadName) {
        Object value = null;
        try {
            System.out.println("ThreadName getCache======" + ThreadName + " 来取值了======");
            boolean flag = true;
            if(flag){
                cache.put((K)"key1",(V)"22");
            }
            //从缓存获取数据
            value = cache.get(keyValue, new Callable<V>() {
                @SuppressWarnings("unchecked")
                public V call() {

                    try {
                        System.out.println("执行业务数据并返回处理结果的数据（访问数据库等）==============" + ThreadName);
                        System.out.println("等待中......");
                        TimeUnit.SECONDS.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return (V) "dataValue";
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return value;
    }


    public static void main(String[] args) {
        final TestGuava<String, String> TestGuava = new TestGuava<String, String>();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("T1======start=======");
                Object value = TestGuava.getCache("key1", "T1");
                System.out.println("T1 value:" + value);
                System.out.println("T1======end========");

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2======start=======");
                Object value = TestGuava.getCache("key2", "T2");
                System.out.println("T2 value:" + value);
                System.out.println("T2======end========");

            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3======start=======");
                Object value = TestGuava.getCache("key1", "T3");
                System.out.println("T3 value:" + value);
                System.out.println("T3======end========");

            }
        });

        t1.start();
        t2.start();
        t3.start();


    }


}

