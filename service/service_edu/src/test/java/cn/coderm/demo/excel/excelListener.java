package cn.coderm.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

//excel读操作监听器
public class excelListener extends AnalysisEventListener<DemoDate> {
    //一行一行读取excel内容
    @Override
    public void invoke(DemoDate date, AnalysisContext analysisContext) {
        System.out.println("****"+date);
    }

    //读取表头内容
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头"+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
