package com.santiago.webRosa.Controller;

import com.santiago.webRosa.Dtos.ChangePasswordDto;
import com.santiago.webRosa.Entities.Publication;
import com.santiago.webRosa.Services.AdminService;
import com.santiago.webRosa.Services.PblService;
import com.santiago.webRosa.Services.TextService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AdminController {


    private final AdminService adminService;
    private final PblService pblService;
    private final TextService textService;

    public AdminController(AdminService adminService, PblService pblService, TextService textService) {
        this.pblService =  pblService;
        this.adminService = adminService;
        this.textService = textService;

    }

    @GetMapping({"/login"})
    public String loggerPage() {

        return "login";
    }

    @GetMapping({"/console/delete"})
    public String deletePublications(Model model) {
        Publication publication = new Publication();
        model.addAttribute("publication", publication);
        model.addAttribute("publications", pblService.getAll());
        adminService.getAll();
        return "delete";
    }

    @GetMapping({"/console/settings"})
    public String adminInfo(Model model) {
        ChangePasswordDto dto = new ChangePasswordDto();
        model.addAttribute("dto", dto);
        return "adminInfo";
    }
    @PostMapping("/console/settings")
    public String upadatePassword(@Valid @ModelAttribute ChangePasswordDto dto, BindingResult result,
                                  Model model,
                                  @RequestParam("currentPassword") String currentPassword,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("validate") String validate,
                                  RedirectAttributes redirectAttributes,
                                  Principal connectedUser) {
        if(result.hasErrors()){
            model.addAttribute("titulo", "form: actualizar información");
            model.addAttribute("dto", dto);

            redirectAttributes.addFlashAttribute("warning", "hubo errores al completar el formulario");
            return "adminInfo";
        }
        dto.setCurrentPassword((currentPassword));
        dto.setNewPassword(newPassword);
        dto.setValidate(validate);
        adminService.updateAdminPassword(dto);
        return "redirect:/console/settings?correct";
    }





    @GetMapping({"/console"})
    public String adminPage(Model model) {
        Publication publication = new Publication();
        model.addAttribute("publication", publication);
        model.addAttribute("publications", pblService.getAll());
        model.addAttribute("pagetext", textService.getText());
        return "admin";
    }

}
