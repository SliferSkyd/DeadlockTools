package com.example.demo.PageReplacement;

import java.util.ArrayList;

public class FIFO extends PageReplacementAlgo {
    public FIFO(int pageFrames, int[] pages) {
        super(pageFrames, pages);
    }

    @Override
    public int getReplacedPage(int i) {
        return 0;
    }

    @Override
    public void run() {
        int n = pages.length;
        int m = frames.length;
        int top = 0;
        for (int i = 0; i < n; ++i) {
            if (checkInFrames(pages[i])) {
                framesList.add(new ArrayList<>());
                continue;
            }
            frames[top] = pages[i];
            top = (top + 1) % m;
            pageFaults++;

            addFrame();
        }
    }
}
