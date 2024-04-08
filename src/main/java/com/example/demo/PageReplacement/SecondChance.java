package com.example.demo.PageReplacement;

import java.util.ArrayList;

public class SecondChance extends PageReplacementAlgo {

    public SecondChance(int pageFrames, int[] pages) {
        super(pageFrames, pages);
        referenceBit = new boolean[pageFrames];
        for (int i = 0; i < pageFrames; ++i) {
            referenceBit[i] = false;
        }
    }

    @Override
    public int getReplacedPage(int i) {
        return 0;
    }

    protected boolean referenceBit[];

    @Override
    public void run() {
        int n = pages.length;
        int m = frames.length;
        int top = 0;
        for (int i = 0; i < n; ++i) {
            if (checkInFrames(pages[i])) {
                framesList.add(new ArrayList<>());
                for (int j = 0; j < m; ++j) {
                    if (frames[j] == pages[i]) {
                        referenceBit[j] = true;
                        break;
                    }
                }
                continue;
            }
            while (referenceBit[top]) {
                referenceBit[top] = false;
                top = (top + 1) % m;
            }
            frames[top] = pages[i];
            referenceBit[top] = true;
            top = (top + 1) % m;
            pageFaults++;

            addFrame();
        }
    }
}
