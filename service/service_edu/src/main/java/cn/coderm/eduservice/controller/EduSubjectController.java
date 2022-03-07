package cn.coderm.eduservice.controller;


import cn.coderm.commonutils.R;
import cn.coderm.eduservice.entity.subject.OneSubject;
import cn.coderm.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author coderm
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin(allowCredentials = "true")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;


    //添加课程分类
    //获取上传过来的文件，把文件内容读取
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //上传的excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

    //课程分类列表(树形)
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list", list);
    }

}

