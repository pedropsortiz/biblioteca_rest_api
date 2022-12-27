package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarUsuario(@RequestBody Usuario usuario){ return usuarioService.criarUsuario(usuario); }

    @GetMapping("/listar")
    public List<Usuario> listarUsuario(){
        return usuarioService.listarUsuario();
    }

    @PostMapping("/editar/{idUsuario}")
    public ResponseEntity<ApiResponse> editarUsuario(@PathVariable("idUsuario") Integer idUsuario, @RequestBody Usuario usuario){
        if (!usuarioService.findById(idUsuario)){
            return new ResponseEntity<>(new ApiResponse(false, "O usuário não existe ou não foi encontrado!"), HttpStatus.OK);
        }
        usuarioService.editarUsuario(idUsuario, usuario);
        return new ResponseEntity<>(new ApiResponse(true, "Alterações salvas com sucesso!"), HttpStatus.OK);
    }

    @PostMapping("/deletar/{idUsuario}")
    public ResponseEntity<ApiResponse> deletarUsuario(@PathVariable("idUsuario") Integer idUsuario){
        if (!usuarioService.findById(idUsuario)){
            return new ResponseEntity<>(new ApiResponse(false, "O usuário não existe ou não foi encontrado!"), HttpStatus.OK);
        }
        usuarioService.deletarUsuario(idUsuario);
        return new ResponseEntity<>(new ApiResponse(true, "O usuário foi deletado com sucesso!"), HttpStatus.OK);
    }

}
