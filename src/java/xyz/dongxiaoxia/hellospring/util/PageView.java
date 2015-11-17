package xyz.dongxiaoxia.hellospring.util;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class PageView {
    /**
     * 分页数据
     */
    private List records;

    /**
     * 页码的开始索引类
     * 这个类包含，
     * startIndex 开始索引
     * endIndex 结束索引
     * 这个数是计算出来的
     */
    private PageIndex pageIndex;

    /**
     * 总页数
     * 这个数是计算出来的
     */
    private long pageCount;

    /**
     * 每页显示几条记录
     */
    private int pageSize = 10;

    /**
     * 默认 当前页 为第一页
     * 这个数是计算出来的
     */
    private int pageNow = 1;

    /**
     * 总记录数
     */
    private long rowCount;

    /**
     * 从第几条记录开始
     */
    private int startPage;

    /**
     * 规定显示5个页码
     */
    private int pageCode = 5;

    public PageView() {

    }

    /**
     * 使用构造函数，强制必须输入
     * 每页显示数量 和 当前页
     *
     * @param pageSize
     * @param pageNow
     */
    public PageView(int pageSize, int pageNow) {
        this.pageSize = pageSize;
        this.pageNow = pageNow;
    }

    /**
     * 使用构造函数，强制必须输入
     *
     * @param pageNow 当前页
     */
    public PageView(int pageNow) {
        this.pageNow = pageNow;
        startPage = (this.pageNow - 1) * this.pageSize;
    }

    /**
     * 要获得记录的开始索引 即 开始页码
     *
     * @return
     */
    public int getFirstResult() {
        return (this.pageNow - 1) * this.pageSize;
    }

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    /**
     * 查询结果方法
     * 把 记录数 结果集合 放到PageView对象
     *
     * @param rowCount 总结果数
     * @param records  结果集合
     */
    public void setQueryResult(long rowCount, List records) {
        setRowCount(rowCount);
        setRecords(records);
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public PageIndex getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(PageIndex pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public long getPageCount() {
        return pageCount;
    }

    /**
     * WebTool这是一个分页工具类
     *
     * @author Administrator
     * <p/>
     * 　pagecode　要获得记录的开始索引　即　开始页码
     * pageNow 　当前页
     * 　pageCount 总页数
     * <p/>
     * 这个工具类　返回的是页索引　PageIndex
     * <p/>
     * 在这个方法中存在一个问题，每页显示数量　和　当前页、、不能为空
     * 必需输入
     */
    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
        this.pageIndex = WebTool.getPageIndex(pageCode, pageNow, pageCount);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
        setPageCount(this.rowCount % this.pageSize == 0 ? this.rowCount / this.pageSize : this.rowCount / pageSize + 1);
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
}
