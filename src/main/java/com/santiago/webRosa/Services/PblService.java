package com.santiago.webRosa.Services;

import com.santiago.webRosa.Entities.Publication;
import com.santiago.webRosa.Repository.PblRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PblService {

    private final PblRepository pblRepository;

    public PblService(PblRepository pblRepository) {
        this.pblRepository = pblRepository;
    }


    public Publication getPublication(Integer id){
        return pblRepository.findById(id).orElse(null);
    }

    public List<Publication> getAll(){
        return pblRepository.findAll();
    }
    public void deletePublication(Integer id){
        pblRepository.deleteById(id);
    }

    public Publication addPublication(Publication publication){
        return pblRepository.save(publication);
    }

}
