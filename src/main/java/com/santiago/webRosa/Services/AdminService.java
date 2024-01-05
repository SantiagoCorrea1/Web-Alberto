package com.santiago.webRosa.Services;

import com.santiago.webRosa.Dtos.ChangePasswordDto;
import com.santiago.webRosa.Entities.Admin;
import com.santiago.webRosa.Repository.AdminRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AdminService implements UserDetailsService {

   private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    public AdminService(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        super();
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    public Admin getAdmin(Integer id){
        return adminRepository.findById(id).orElse(null);
    }


    public Admin updateAdminPassword(ChangePasswordDto dto) throws UsernameNotFoundException{

        //Obtener los detalles del usuario a través de UserDetailsService
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = (Admin) loadUserByUsername(username);

        if(!passwordEncoder.matches(dto.getCurrentPassword(), admin.getPassword())){
            throw new ValidationException("la contraseña es incorrecta");
        }
        if (!dto.getNewPassword().equals(dto.getValidate())){
            throw new ValidationException("las contraseñas no coinciden");
        }
        if (dto.getNewPassword().length() < 4) {
            throw new IllegalArgumentException("El campo debe tener almenos 4 caracteres");
        }
        admin.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        return adminRepository.save(admin);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return admin;
    }

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }

}
