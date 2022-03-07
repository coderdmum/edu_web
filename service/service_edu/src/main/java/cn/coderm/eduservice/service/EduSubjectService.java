package cn.coderm.eduservice.service;

import cn.coderm.eduservice.entity.EduSubject;
import cn.coderm.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author coderm
 * @since 2022-02-20
 */
public interface EduSubjectService extends IService<EduSubject> {
    void saveSubject(MultipartFile file, EduSubjectService subjectService);


    List<OneSubject> getAllOneTwoSubject();

}
