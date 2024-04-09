package com.example.demo.PageReplacement;

public class LRU extends PageReplacementAlgo {

    public LRU(int pageFrames, int[] pages) {
        super(pageFrames, pages);
    }

    @Override
    public int getReplacedPage(int idx) {
        int minUsedTime = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < frames.length; ++i) {
            assert(frames[i] != -1);
            if (lastTimeUsed[frames[i]] < minUsedTime) {
                minUsedTime = lastTimeUsed[frames[i]];
                pos = i;
            }
        }
        return pos;
    }
}
