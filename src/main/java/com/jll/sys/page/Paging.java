package com.jll.sys.page;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Daffy on 2019/7/11.
 */
@Entity
@Table(name = "paging")
public class Paging {
    @Id
    public int pageNo;
    public int pageSize;
    public int pageCount;
    public int totalRecordCount;

    public Paging(int pageNo, int pageSize, int pageCount, int totalRecordCount) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalRecordCount = totalRecordCount;
    }

    public Paging() {
        super();
    }


}