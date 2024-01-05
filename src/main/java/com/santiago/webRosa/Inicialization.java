package com.santiago.webRosa;

import com.santiago.webRosa.Entities.Admin;
import com.santiago.webRosa.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Inicialization implements CommandLineRunner{

   private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Inicialization(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        /*Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("4540660"));
        adminRepository.save(admin);*/
    }
}

