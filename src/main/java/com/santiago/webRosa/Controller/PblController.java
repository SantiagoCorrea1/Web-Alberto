package com.santiago.webRosa.Controller;

import com.santiago.webRosa.Dtos.PageTextDto;
import com.santiago.webRosa.Entities.Publication;
import com.santiago.webRosa.Services.PblService;
import com.santiago.webRosa.Services.TextService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class PblController {

    private final PblService pblService;
    private final TextService textService;

    public PblController(PblService pblService, TextService textService) {
        this.pblService = pblService;
        this.textService = textService;
    }


    @GetMapping({"/", "/homePage"})
    public String mainPage(Model model){
        Publication publication = new Publication();
        model.addAttribute("publication", publication);
        model.addAttribute("publications", pblService.getAll());
        model.addAttribute("pagetext", textService.getText());
        return "index";
    }


    @GetMapping({"/console/delete/{id}"})
    public String deletePublication(@PathVariable Integer id){
        pblService.deletePublication(id);
        return "redirect:/console/delete?correct";
    }


    @PostMapping ({ "/homePage", "/console"})
    public String createPublication(@Valid @ModelAttribute Publication publication, BindingResult result,
                                    Model model, @RequestParam("image") MultipartFile img, RedirectAttributes attributes,
                                    HttpServletRequest request) throws IOException {
        String currentUrl = request.getRequestURI();
        if(result.hasErrors()){
            model.addAttribute("titulo", "form: nueva publicación");
            model.addAttribute("publication", publication);

            attributes.addFlashAttribute("warning", "hubo errores al completar el formulario");

            if(currentUrl.equals("/homePage")) {
                return "index";
            } else if (currentUrl.equals("/console")){
                return "admin";
            }
        }
        if(!img.isEmpty()){
            Path urlDirectory = Paths.get("src//main//resources//static//site//images");
            String absoluteUrl = urlDirectory.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = img.getBytes();
                Path completeUrl = Paths.get(absoluteUrl + "//" + img.getOriginalFilename());
                Files.write(completeUrl, bytesImg);

                publication.setUrlImage("/site/images/" + img.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Date date = new Date();
        SimpleDateFormat f =  new SimpleDateFormat("dd/MM/yyyy");
        publication.setPublicationDate(f.format(date));
        pblService.addPublication(publication);
        attributes.addFlashAttribute("success", "la publicación ha sido añadida correctamente");
        if(currentUrl.equals("/homePage")){
            return "redirect:/homePage";
        } else if(currentUrl.equals("/console")){
            return "redirect:/console";
        }
        return "error";
    }

    @GetMapping({"/console/edit"})
    public String getEditPage(Model model) {
        model.addAttribute("pageText", textService.getText());
        return "edit";
    }

    @PostMapping({"/console/edit"})
    public String updatePageText(@Valid @ModelAttribute PageTextDto dto, BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("titulo", "form: cambiar texto");
            model.addAttribute("dto", dto);

            redirectAttributes.addFlashAttribute("warning", "hubo errores al completar el formulario");
            return "edit";
        }
        if (dto.getText().length()>=1000 || dto.getText().length()>=250){
            redirectAttributes.addFlashAttribute("el texto o título se exceden en el tamaño máximo");
        } else {
            textService.updateHeader(dto.getTitle(), dto.getText());
            redirectAttributes.addFlashAttribute("se ha actualizado exitosamente el texto");
        }
        return "redirect:/console/edit";
    }



}
