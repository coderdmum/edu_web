package cn.coderm.eduservice.service;

import cn.coderm.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author coderm
 * @since 2022-02-19
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //分页查询讲师的方法
    Map<String, Object> getTeahcerFrontList(Page<EduTeacher> pageTeacher);

}
