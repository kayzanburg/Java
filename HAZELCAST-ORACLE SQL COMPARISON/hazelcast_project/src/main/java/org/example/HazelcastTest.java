package org.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HazelcastTest {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        // 20,000 sayının eklenmesi ve alınması
        IMap<Integer, Integer> map = hazelcastInstance.getMap("testMap");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            map.put(i, i);
        }
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("20.000 sayının eklenme süresi: " + duration + " ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            map.get(i);
        }
        duration = System.currentTimeMillis() - startTime;
        System.out.println("20.000 sayının rastgele alınma süresi: " + duration + " ms");

        // 100,000 sayının eklenmesi ve alınması
        IMap<Integer, Integer> map2 = hazelcastInstance.getMap("testMap2");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            map2.put(i, i);
        }
        duration = System.currentTimeMillis() - startTime;
        System.out.println("100.000 sayının eklenme süresi: " + duration + " ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            map2.get(i);
        }
        duration = System.currentTimeMillis() - startTime;
        System.out.println("100.000 sayının rastgele alınma süresi: " + duration + " ms");

        hazelcastInstance.shutdown();
    }
}
