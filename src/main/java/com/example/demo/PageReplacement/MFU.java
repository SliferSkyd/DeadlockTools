package com.example.demo.PageReplacement;

public class MFU extends PageReplacementAlgo {
    public MFU(int pageFrames, int[] pages) {
        super(pageFrames, pages);
    }

    @Override
    public int getReplacedPage(int idx) {
        int maxUsed = -1;
        int pos = -1;
        for (int i = 0; i < frames.length; ++i) {
            assert(frames[i] != -1);
            if (cntUsed[frames[i]] > maxUsed) {
                maxUsed = cntUsed[frames[i]];
                pos = i;
            } else if (cntUsed[frames[i]] == maxUsed && timeIn[frames[i]] < timeIn[frames[pos]]) {
                pos = i;
            }
        }
        return pos;
    }
}
