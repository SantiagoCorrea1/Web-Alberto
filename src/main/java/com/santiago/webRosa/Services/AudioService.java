package com.santiago.webRosa.Services;

import com.santiago.webRosa.Entities.Audio;
import com.santiago.webRosa.Repository.AudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioService {

    private final AudioRepository audioRepository;

    public AudioService(AudioRepository audioRepository) {
        this.audioRepository = audioRepository;
    }

    public Audio newAudio(Audio audio){
        return audioRepository.save(audio);
    }

    public void deleteAudio(Integer id){
        audioRepository.deleteById(id);
    }

    public List<Audio> getAllAudios(){
        return audioRepository.findAll();
    }

    public Audio getAudio(Integer id){
        return audioRepository.findById(id).orElse(null);
    }
}
