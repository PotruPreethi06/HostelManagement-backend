package com.example.hostelmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.config.JwtService;
import com.example.hostelmanagement.dto.AdminLoginRequest;
import com.example.hostelmanagement.dto.LoginResponse;
import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // =========================
    // CREATE ADMIN
    // =========================
    public Admin createAdmin(Admin admin) {

        String encodedPassword =
                passwordEncoder.encode(admin.getPassword());

        admin.setPassword(encodedPassword);

        return adminRepository.save(admin);
    }

    // =========================
    // GET ALL ADMINS
    // =========================
    public List<Admin> getAllAdmins() {

        return adminRepository.findAll();
    }

    // =========================
    // GET ADMIN BY ID
    // =========================
    public Admin getAdminById(Long id) {

        return adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));
    }

    // =========================
    // GET ADMIN BY EMAIL
    // =========================
    public Admin getAdminByEmail(String email) {

        return adminRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));
    }

    // =========================
    // UPDATE ADMIN
    // =========================
    public Admin updateAdmin(Long id, Admin updatedAdmin) {

        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));

        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());

        // Encode the new password only if provided
        if (updatedAdmin.getPassword() != null
                && !updatedAdmin.getPassword().isBlank()) {

            existingAdmin.setPassword(
                    passwordEncoder.encode(
                            updatedAdmin.getPassword()
                    )
            );
        }

        existingAdmin.setPhone(updatedAdmin.getPhone());
        existingAdmin.setRole(updatedAdmin.getRole());

        return adminRepository.save(existingAdmin);
    }

    // =========================
    // ADMIN LOGIN + JWT
    // =========================
    public LoginResponse login(AdminLoginRequest loginRequest) {

        // Temporary debugging: check received email
        System.out.println(
                "Login email received: "
                + loginRequest.getEmail()
        );

        // Find admin by email
        Admin admin = adminRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        ));

        // Temporary debugging: confirm admin was found
        System.out.println(
                "Admin found: " + admin.getEmail()
        );

        System.out.println(
                "Password hash exists: "
                + (admin.getPassword() != null
                && !admin.getPassword().isBlank())
        );

        // Verify password using BCrypt
        boolean passwordMatches =
                loginRequest.getPassword() != null
                && admin.getPassword() != null
                && passwordEncoder.matches(
                        loginRequest.getPassword(),
                        admin.getPassword()
                );

        // Temporary debugging: print match result only
        System.out.println(
                "Password matches: " + passwordMatches
        );

        if (!passwordMatches) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        // Generate JWT token
        String token = jwtService.generateToken(
                admin.getEmail(),
                admin.getRole()
        );

        // Return successful login response
        return new LoginResponse(
                "Login successful",
                token,
                admin.getName(),
                admin.getEmail(),
                admin.getRole()
        );
    }

    // =========================
    // DELETE ADMIN
    // =========================
    public String deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Admin not found"));

        adminRepository.delete(admin);

        return "Admin deleted successfully";
    }
}
