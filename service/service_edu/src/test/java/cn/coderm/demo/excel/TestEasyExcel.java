package cn.coderm.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args){
        //实现excel写操作
        //1. 设置写入文件地址和excel文件名称
//        String filename = "D:\\write.xlsx";
//
//        //2.调用easyExcel里面的方法实现具体方法(1.文件名   2.参数实体类)
//        EasyExcel.write(filename, DemoDate.class).sheet("学生列表").doWrite(getDate());
        //实现excel读操作
        String filename = "D:\\write.xlsx";
        EasyExcel.read(filename, DemoDate.class, new excelListener()).sheet().doRead();

    }

    //创建方法返回list集合
    private static List<DemoDate> getDate(){
        List<DemoDate> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            DemoDate date = new DemoDate();
            date.setSno(i);
            date.setSname("lucy" + i);
            list.add(date);
        }
        return list;
    }
}
