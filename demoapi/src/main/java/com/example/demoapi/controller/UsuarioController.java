package com.example.demoapi.controller;

import com.example.demoapi.model.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private List<Usuario> listaUsuarios = new ArrayList<>();
    private Long contadorId = 1L;

    //GET - Listar todos
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return listaUsuarios;
    }

    //GET - Buscar por ID
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return listaUsuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    //POST - Crear usuario
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        usuario.setId(contadorId++);
        listaUsuarios.add(usuario);
        return usuario;
    }

    // PUT - Actualizar usuario
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {

        Optional<Usuario> usuarioOptional = listaUsuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            return usuario;
        }

        return null;
    }

    // DELETE - Eliminar usuario
    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        listaUsuarios.removeIf(u -> u.getId().equals(id));
        return "Usuario eliminado correctamente";
    }
}