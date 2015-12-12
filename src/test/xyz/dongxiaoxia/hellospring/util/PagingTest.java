package xyz.dongxiaoxia.hellospring.util;

import org.junit.Test;

/**
 * Created by Administrator on 2015/12/12.
 */
public class PagingTest {

    @Test
    public void testd() {
        Paging paging = new Paging(3, 10);
        System.out.println("getPageStart():" + paging.getPageStart());
        System.out.println("getPageSize():" + paging.getPageSize());
        System.out.println("getStartRecord():" + paging.getStartRecord());
        System.out.println("getEndRecord():" + paging.getEndRecord());
        System.out.println("getData():" + paging.getData());
        System.out.println("getPageCount():" + paging.getPageCount());
        System.out.println("getTotalRecord():" + paging.getTotalRecord());

    }
}
