package LD;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TimeMap {

    private record Data(String val, int time) { }

    Map<String, List<Data>> map;

    public TimeMap() {
        map = new ConcurrentHashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key))
            map.put(key, new ArrayList<>());

        map.get(key).add(new Data(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        return binarySearch(map.get(key), timestamp);
    }

    private String binarySearch(List<Data> list, int timestamp) {
        int start = 0;
        int end = list.size() - 1;
        int idx = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid).time <= timestamp) {
                idx = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return idx != -1 ? list.get(idx).val : "";
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();

        // Set values with timestamps
        timeMap.set("foo", "bar", 1);
        timeMap.set("foo", "bar2", 2);

        // Get the values at different timestamps
        System.out.println("Get 'foo' at timestamp 1: " + timeMap.get("foo", 1)); // Should print: bar
        System.out.println("Get 'foo' at timestamp 2: " + timeMap.get("foo", 2)); // Should print: bar2
        System.out.println("Get 'foo' at timestamp 3: " + timeMap.get("foo", 3)); // Should print: bar2
        System.out.println("Get 'foo' at timestamp 0: " + timeMap.get("foo", 0)); // Should print: ""

        // Set another value with timestamp
        timeMap.set("foo", "bar3", 4);

        // Get the values at different timestamps
        System.out.println("Get 'foo' at timestamp 4: " + timeMap.get("foo", 4)); // Should print: bar3
        System.out.println("Get 'foo' at timestamp 5: " + timeMap.get("foo", 5)); // Should print: bar3
    }


}
