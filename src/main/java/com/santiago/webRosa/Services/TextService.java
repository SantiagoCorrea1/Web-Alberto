package com.santiago.webRosa.Services;

import com.santiago.webRosa.Entities.PageText;
import com.santiago.webRosa.Repository.TextRepository;
import org.springframework.stereotype.Service;


@Service
public class TextService {

    private final TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }


    public PageText getText(){
        return textRepository.getReferenceById(1);
    }

    public PageText updateHeader(String header, String body) {
        PageText pageText = textRepository.getReferenceById(1);
        pageText.setTitle(header);
        pageText.setText(body);
        return textRepository.save(pageText);
    }

}
