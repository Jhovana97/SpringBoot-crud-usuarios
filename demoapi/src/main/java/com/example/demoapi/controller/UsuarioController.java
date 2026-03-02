package com.example.demoapi.controller;

import com.example.demoapi.model.Usuario;
import com.example.demoapi.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;
//este import es para validar los datos de entrada, por ejemplo, que el email tenga formato correcto, que el nombre no esté vacío, etc.

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    //@PostMapping
    //public Usuario crearUsuario(@RequestBody Usuario usuario) {
    //    return usuarioService.crearUsuario(usuario);
    //}
// He comentado el método anterior para agregar validación de datos de entrada
// Ahora el método espera un objeto Usuario con validación, y si los datos no cumplen las reglas (por ejemplo, email no válido, nombre vacío, etc.),
//  Spring automáticamente devolverá un error 400 Bad Request con detalles del error.
    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    //@PutMapping("/{id}")
    //public Usuario actualizarUsuario(
    //        @PathVariable Long id,
    //        @RequestBody Usuario usuario) {
    //    return usuarioService.actualizarUsuario(id, usuario);
    //}
    
    // He comentado el método anterior para agregar validación de datos de entrada
    // Ahora el método espera un objeto Usuario con validación, y si los datos no cumplen
    //solo se agrega la anotación @Valid para validar los datos de entrada, igual que en el método POST

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);

        return ResponseEntity.ok().body(
                java.util.Map.of(
                        "mensaje", "Usuario eliminado correctamente",
                        "status", 200
                )
        );
    }
}