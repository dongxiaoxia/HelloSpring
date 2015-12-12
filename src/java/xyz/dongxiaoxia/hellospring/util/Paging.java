package xyz.dongxiaoxia.hellospring.util;

import java.util.List;

/**
 * Created by dongxiaoxia on 2015/11/28.
 * <p/>
 * 封装分页信息
 */
public class Paging<T> {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private int pageStart;//起始页
    private int totalRecord;//总条数
    private int pageSize;//每页显示条数
    private long pageCount;//总页数
    private List<T> data;//页数据

    /**
     * 使用构造函数，强制必须输入
     * 每页显示数量 和 当前页
     *
     * @param pageStart
     * @param pageSize
     */
    public Paging(int pageStart, int pageSize) {
        this.pageStart = pageStart;
        this.pageSize = pageSize;
    }

    public Paging(String pageStart, String pageSize) {
        this.pageStart = pageStart == null ? 1 : Integer.parseInt(pageStart);
        this.pageSize = pageSize == null ? 10 : Integer.parseInt(pageSize);
    }

    /**
     * 总页数
     * 这个数是计算出来的
     */
    public long getPageCount() {
        if (pageSize <= 0) {
            return 0;
        }
        pageCount = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        //pageCount =(totalRecord + pageSize - 1)/pageSize;
        return pageCount;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    //起始行,当前页第一条数据在List中的位置,从0开始
    public int getStartRecord() {
        /*if ((pageStart - 1) * pageSize > totalRecord) {
            return totalRecord;
        } else {
            return (pageStart - 1) * pageSize;
        }*/
        return (pageStart - 1) * pageSize;
    }

    //结束行
    public int getEndRecord() {
        return getStartRecord() + getPageSize() - 1;
    }
}
