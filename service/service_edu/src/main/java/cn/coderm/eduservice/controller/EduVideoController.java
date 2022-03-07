package cn.coderm.eduservice.controller;


import cn.coderm.commonutils.R;
import cn.coderm.eduservice.client.VodClient;
import cn.coderm.eduservice.entity.EduVideo;
import cn.coderm.eduservice.service.EduVideoService;
import cn.coderm.servicebase.exceptionhandler.MException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author coderm
 * @since 2022-02-20
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin(allowCredentials = "true")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;
    //注入VodClient
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    //删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new MException(20001, "删除视频失败，熔断器");
            }
        }

        //删除小节
        videoService.removeById(id);
        return R.ok();
    }
}

