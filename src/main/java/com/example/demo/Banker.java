package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Banker {
    private int available[];
    private int allocation[][];
    private int need[][];
    private int n;
    private int m;

    public Banker(int available[], int max[][], int allocation[][]) {
        this.available = available;
        this.allocation = allocation;
        n = max.length;
        m = max[0].length;
        need = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }
    public List<Integer> solve() {
        List<Integer> order = new ArrayList<>();
        boolean[] finish = new boolean[n];
        for (int i = 0; i < n; ++i) {
            finish[i] = false;
        }
        while (true) {
            boolean done = false;
            for (int i = 0; i < n; ++i) {
                if (finish[i]) {
                    continue;
                }
                boolean ok = true;
                for (int j = 0; j < m; ++j) {
                    if (need[i][j] > available[j]) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    for (int j = 0; j < m; ++j) {
                        available[j] += allocation[i][j];
                    }
                    order.add(i);
                    finish[i] = true;
                    done = true;
                }
            }
            if (!done) {
                break;
            }
        }
        return order;
    }
}
