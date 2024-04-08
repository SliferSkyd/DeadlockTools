package com.example.demo.PageReplacement;

import java.util.ArrayList;
import java.util.List;

public abstract class PageReplacementAlgo {

    protected int pageFaults;
    protected int[] pages;
    protected int[] frames;
    protected int[] timeIn;
    protected int[] timeUsed;
    protected int[] cntUsed;
    protected List<List<Integer>> framesList = new ArrayList<List<Integer>>();

    public PageReplacementAlgo(int pageFrames, int[] pages) {
        this.pages = pages;
        frames = new int[pageFrames];
        pageFaults = 0;
        int maxIdx = 0;
        for (int i = 0; i < pages.length; ++i) {
            maxIdx = Math.max(maxIdx, pages[i]);
        }
        cntUsed = new int[maxIdx + 1];
        timeIn = new int[maxIdx + 1];
        timeUsed = new int[maxIdx + 1];
        for (int i = 0; i < maxIdx + 1; ++i) {
            cntUsed[i] = 0;
            timeIn[i] = -1;
            timeUsed[i] = -1;
        }
        for (int i = 0; i < pageFrames; ++i) {
            frames[i] = -1;
        }
    }

    public abstract int getReplacedPage(int i);

    public void run() {
        int n = pages.length;
        int m = frames.length;
        for (int i = 0; i < n; ++i) {
            timeUsed[pages[i]] = i;
            cntUsed[pages[i]]++;
            if (checkInFrames(pages[i])) {
                framesList.add(new ArrayList<>());
                continue;
            }
            boolean full = true;
            for (int j = 0; j < m; ++j) {
                if (frames[j] == -1) {
                    frames[j] = pages[i];
                    full = false;
                    break;
                }
            }
            if (full) {
                int pos = getReplacedPage(i);
                frames[pos] = pages[i];
            }
            timeIn[pages[i]] = i;
            pageFaults++;
            addFrame();
        }
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public List<List<Integer>> getFramesList() {
        return framesList;
    }

    protected boolean checkInFrames(int page) {
        for (int i = 0; i < frames.length; ++i) {
            if (frames[i] == page) return true;
        }
        return false;
    }

    protected void addFrame() {
        List<Integer> frame = new ArrayList<>();
        for (int j = 0; j < frames.length; ++j) {
            if (frames[j] == -1) break;
            frame.add(frames[j]);
        }
        framesList.add(frame);
    }
}
