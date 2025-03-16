package com.preparation.algorithm.dp.practice;

/**
 * 1. Queue :
 * 2. Deque (I) : same method name as Queue with first, last highlighting the positions (ArrayDeque, LinkedList)
 * 3. CustomComparator implements Comparator / Collections.reverseOrder()
 * 4. CustomKey in Map using equals and hashCode;
 * 5. TreeMap
 * 6. String to charArray : str.toCharArray();
 * 7. char to int : ch-'a' = index;
 * 8. XOR : ^
 *
 */

import java.util.*;

public class DpPractice {


    public static void main(String... s) {

        Map<NodeEx, String> map = new HashMap<>();
        NodeEx ex1 = new NodeEx(1,3);
        map.put(new NodeEx(1,3), "ex1");

        System.out.println(map.get(ex1));

        TreeMap<NodeEx, String> treeMap = new TreeMap<>();
        treeMap.floorEntry(ex1);
        treeMap.ceilingKey(ex1);


    }

}

class NodeEx {
    int a;
    int b;

    public NodeEx(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int hashCode() {
        return Integer.hashCode(a);
    }

    public boolean equals(Object second) {
        NodeEx firEx = (NodeEx) this;
        NodeEx secEx = (NodeEx) second;
        if ((firEx.a == secEx.a) && (firEx.b == secEx.b)) {
            return true;
        }
        return false;
    }

}



