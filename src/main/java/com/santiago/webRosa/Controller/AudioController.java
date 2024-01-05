package com.santiago.webRosa.Controller;

import com.santiago.webRosa.Entities.Audio;
import com.santiago.webRosa.Services.AudioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AudioController {

    private final AudioService audioService;

    public AudioController(AudioService audioService) {
        this.audioService = audioService;
    }

    @GetMapping("/audios")
    public String viewPage(Model model){
        Audio audio = new Audio();
        model.addAttribute("audio", audio);
        model.addAttribute("audios", audioService.getAllAudios());
        return "audios";
    }
}
