package cn.coderm.eduservice.listener;

import cn.coderm.eduservice.entity.EduSubject;
import cn.coderm.eduservice.entity.excel.SubjectData;
import cn.coderm.eduservice.service.EduSubjectService;
import cn.coderm.servicebase.exceptionhandler.MException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他内容
    //不能实现数据库操作
    public EduSubjectService subjectService;
    public SubjectExcelListener(){
    }
    public SubjectExcelListener(EduSubjectService subjectService){
        this.subjectService = subjectService;
    }


    //读取excel内容一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new MException(20001, "文件数据为空");
        }
        //一行一行读取，第一个为一级分类，第二个为二级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null){
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName()); ///一级分类名称

            subjectService.save(existOneSubject);
        }
        String pid = existOneSubject.getId();
        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName()); ///二级分类名称
            subjectService.save(existTwoSubject);
        }
    }
    //判定一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }


    //判定二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
