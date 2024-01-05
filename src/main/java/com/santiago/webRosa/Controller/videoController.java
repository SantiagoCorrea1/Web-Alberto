package com.santiago.webRosa.Controller;

import com.santiago.webRosa.Entities.Video;
import com.santiago.webRosa.Services.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class videoController {

    private final VideoService videoService;

    public videoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/videos")
    public String viewPage(Model model){
        Video video = new Video();
        model.addAttribute("video", video);
        model.addAttribute("videos", videoService.getAllVideos());
        return "videos";
    }
}
