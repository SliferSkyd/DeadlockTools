package com.example.demo.PageReplacement;

public class MRU extends PageReplacementAlgo {
    public MRU(int pageFrames, int[] pages) {
        super(pageFrames, pages);
    }

    @Override
    public int getReplacedPage(int idx) {
        int maxUsedTime = -1;
        int pos = -1;
        for (int i = 0; i < frames.length; ++i) {
            assert(frames[i] != -1);
            if (timeUsed[frames[i]] > maxUsedTime) {
                maxUsedTime = timeUsed[frames[i]];
                pos = i;
            }
        }
        return pos;
    }
}
