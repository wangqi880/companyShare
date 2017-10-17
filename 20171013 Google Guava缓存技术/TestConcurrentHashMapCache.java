import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by luojian on 2017/10/10.
 * <p>
 * 1,测试ConcurrentHashMap并发访问场景：相同的业务处理三次
 * （高并发场景会同时出现多次后台数据请求，与缓存的初衷背离）
 * 2,给取值方法getCache()加同步锁synchronized，实现请求串行
 * (问题：加上Synchronized变成串行，这样在高并发行时性能也下降了)
 */
public class TestConcurrentHashMapCache<K, V> {
    private ConcurrentHashMap<K, V> cacheMap = new ConcurrentHashMap<K, V>();

    public /*synchronized*/ Object getCache(K keyValue, String ThreadName) {
        System.out.println("ThreadName getCache======" + ThreadName + " 来取值了======");
        Object value = null;
        //从缓存获取数据
        value = cacheMap.get(keyValue);
        //如果没有的话，把数据放到缓存
        if (value == null) {
            return putCache(keyValue, ThreadName);
        }
        return value;
    }

    public Object putCache(K keyValue, String ThreadName) {
        @SuppressWarnings("unchecked")
        V value = null;
        try {
            System.out.println("执行业务数据并返回处理结果的数据（访问数据库等）==============" + ThreadName);
            System.out.println("等待中......");
            TimeUnit.SECONDS.sleep(10);
            //可以根据业务从数据库获取等取得数据,这边就模拟已经获取数据了
            value = (V) "dataValue";
            //把数据放到缓存
            cacheMap.put(keyValue, value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
        final TestConcurrentHashMapCache<String, String> TestGuaVA = new TestConcurrentHashMapCache<String, String>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1======start========");
                Object value = TestGuaVA.getCache("key", "T1");
                System.out.println("T1 value：" + value);
                System.out.println("T1======end========");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2======start========");
                Object value = TestGuaVA.getCache("key", "T2");
                System.out.println("T2 value：" + value);
                System.out.println("T2======end========");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T3======start========");
                Object value = TestGuaVA.getCache("key", "T3");
                System.out.println("T3 value：" + value);
                System.out.println("T3======end========");
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
