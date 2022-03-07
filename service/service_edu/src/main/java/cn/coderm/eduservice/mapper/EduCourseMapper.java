package cn.coderm.eduservice.mapper;

import cn.coderm.eduservice.entity.EduCourse;
import cn.coderm.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author coderm
 * @since 2022-02-20
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
    //根据课程id，编写sql语句查询课程信息
//    CourseWebVo getBaseCourseInfo(String courseId);

}
