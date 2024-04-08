package com.example.demo.PageReplacement;

import java.util.ArrayList;

public class Optimal extends PageReplacementAlgo {
    public Optimal(int pageFrames, int[] pages) {
        super(pageFrames, pages);
    }

    @Override
    public int getReplacedPage(int idx) {
        int maxNextUsed = -1;
        int minInTime = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < frames.length; ++i) {
            assert(frames[i] != -1);
            int next = getNextUsed(idx, frames[i]);
            if (next > maxNextUsed) {
                maxNextUsed = next;
                minInTime = timeIn[i];
                pos = i;
            } else if (next == maxNextUsed && timeIn[i] < minInTime) {
                minInTime = timeIn[i];
                pos = i;
            }
        }
        return pos;
    }

    private int getNextUsed(int start, int page) {
        int n = pages.length;
        for (int i = start; i < n; ++i) {
            if (pages[i] == page) return i;
        }
        return n;
    }
}
