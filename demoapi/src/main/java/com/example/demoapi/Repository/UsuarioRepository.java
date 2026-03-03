package com.example.demoapi.Repository;

import com.example.demoapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;   // 👈 ESTE IMPORT ES NUEVO

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);   // 👈 AQUÍ VA

}