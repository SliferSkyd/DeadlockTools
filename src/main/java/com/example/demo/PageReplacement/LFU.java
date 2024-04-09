package com.example.demo.PageReplacement;

public class LFU extends PageReplacementAlgo {
    public LFU(int pageFrames, int[] pages) {
        super(pageFrames, pages);
    }

    @Override
    public int getReplacedPage(int idx) {
        int minUsed = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < frames.length; ++i) {
            assert(frames[i] != -1);
            if (cntUsed[frames[i]] < minUsed) {
                minUsed = cntUsed[frames[i]];
                pos = i;
            } else if (cntUsed[frames[i]] == minUsed && timeIn[frames[i]] < timeIn[frames[pos]]) {
                pos = i;
            }
        }
        return pos;
    }
}
