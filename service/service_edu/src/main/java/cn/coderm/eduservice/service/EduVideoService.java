package cn.coderm.eduservice.service;

import cn.coderm.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author coderm
 * @since 2022-02-20
 */
public interface EduVideoService extends IService<EduVideo> {
    //根据课程id删小节
    void removeVideoByCourseId(String courseId);

}
