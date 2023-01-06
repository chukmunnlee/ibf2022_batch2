package sdf;

import java.util.HashMap;
import java.util.Map;

public class TestMap {

    public static void main(String[] args) {
        Map<String, Integer> nums = new HashMap<>();
        System.out.printf("Has key apple: %s\n", nums.get("apple"));

        nums.put("apple", 10);
        nums.put("orange", 20);
        nums.put("pear", 30);

        System.out.printf("Has key apple: %d\n", nums.get("apple"));

        for (String key: nums.keySet()) {
            System.out.printf("key: %s\n", key);
            System.out.printf("key: %s, value: %d\n", key, nums.get(key));
        }
    }
    
}
