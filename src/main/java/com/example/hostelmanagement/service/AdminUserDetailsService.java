package com.example.hostelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hostelmanagement.entity.Admin;
import com.example.hostelmanagement.repository.AdminRepository;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Admin not found with email: " + email
                        )
                );

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .authorities(
                        new SimpleGrantedAuthority(
                                "ROLE_" + admin.getRole()
                        )
                )
                .build();
    }
}