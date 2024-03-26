package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class DeadlockDetection {
    private int available[];
    private int request[][];
    private int allocation[][];
    private int n;
    private int m;

    public DeadlockDetection(int available[], int request[][], int allocation[][]) {
        this.available = available;
        this.request = request;
        this.allocation = allocation;
        n = request.length;
        m = request[0].length;
    }

    public List<Integer> solve() {
        List<Integer> remain = new ArrayList<>();
        boolean[] finish = new boolean[n];
        for (int i = 0; i < n; ++i) {
            finish[i] = true;
            for (int j = 0; j < m; ++j) {
                if (allocation[i][j] != 0) {
                    finish[i] = false;
                    break;
                }
            }
        }
        while (true) {
            boolean done = false;
            for (int i = 0; i < n; ++i) {
                if (finish[i]) {
                    continue;
                }
                boolean ok = true;
                for (int j = 0; j < m; ++j) {
                    if (request[i][j] > available[j]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    for (int j = 0; j < m; ++j) {
                        available[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    done = true;
                }
            }
            if (!done) {
                break;
            }
        }
        for (int i = 0; i < n; ++i)
            if (!finish[i])
                remain.add(i);
        return remain;
    }
}
