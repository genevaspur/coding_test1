package com.hslee.coding_test1.solve;

import java.util.*;

public class Question8 {
    public String solve(int n, int m) throws Exception {
        Deque<Integer> deque = new LinkedList<>();
        StringJoiner result = new StringJoiner(", ", "<", ">");

        for (int i = 1; i <= n; i++) {
            deque.addLast(i);
        }

        while (!deque.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                deque.addLast(deque.pollFirst());
            }
            result.add(Objects.requireNonNull(deque.pollFirst()).toString());
        }

        return result.toString();
    }
}
