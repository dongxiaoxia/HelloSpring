package xyz.dongxiaoxia.hellospring.util;

/**
 * Created by Administrator on 2015/11/17.
 */
public class PageIndex {
    private long startindex;
    private long endindex;

    public PageIndex(long startindex, long endindex) {
        this.startindex = startindex;
        this.endindex = endindex;
    }

    public long getStartindex() {
        return startindex;
    }

    public void setStartindex(long startindex) {
        this.startindex = startindex;
    }

    public long getEndindex() {
        return endindex;
    }

    public void setEndindex(long endindex) {
        this.endindex = endindex;
    }
}
