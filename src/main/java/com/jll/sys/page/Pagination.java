package com.jll.sys.page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daffy on 2019/7/11.
 */
public class Pagination<T> {
    public List<T> data;
    public Paging paging;

    public Pagination() {
        this.data = new ArrayList<T>();
        this.paging = new Paging();
    }

//	public Pagination (Page<T> p) {
//		this.data = p.getContent();
//		this.paging = new Paging();
//		this.paging.pageCount = p.getTotalPages();
//		this.paging.pageNo = p.getNumber();
//		this.paging.pageSize = p.getSize();
//		this.paging.totalRecordCount = p.getTotalElements();
//	}
}
