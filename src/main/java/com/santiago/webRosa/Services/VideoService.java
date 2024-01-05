package com.santiago.webRosa.Services;

import com.santiago.webRosa.Entities.Video;
import com.santiago.webRosa.Repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video newVideo(Video video){
        return videoRepository.save(video);
    }

    public void deleteVideo(Integer id){
        videoRepository.deleteById(id);
    }

    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    public Video getAudio(Integer id){
        return videoRepository.findById(id).orElse(null);
    }
}
