package com.demo.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class ExcellExportUtils {


    //标题名字
    private String[] rowName;

    //每行作为一个object对象
    private List<Object[]> dataList;

    public ExcellExportUtils(String[] rowName,List<Object[]> dataList){
        this.rowName =rowName;
        this.dataList = dataList;
    }

    public InputStream export()throws Exception{

        return null;
    }
}
