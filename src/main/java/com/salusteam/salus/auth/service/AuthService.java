package com.salusteam.salus.auth.service;

import com.salusteam.salus.auth.dto.AuthRequestDTO;
import com.salusteam.salus.auth.dto.AuthResponseDTO;
import com.salusteam.salus.auth.dto.LoginRequestDTO;
import com.salusteam.salus.auth.enums.UserRole;
import com.salusteam.salus.auth.model.User;
import com.salusteam.salus.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthResponseDTO register(AuthRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .passwordHash(dto.getPassword())
                .role(UserRole.PATIENT)
                .build();

        userRepository.save(user);
        return new AuthResponseDTO(user.getId(), user.getName(), user.getRole().name());
    }

    public AuthResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!dto.getPassword().equals(user.getPasswordHash())) {
            throw new RuntimeException("Senha inválida");
        }

        return new AuthResponseDTO(user.getId(), user.getName(), user.getRole().name());
    }
}

