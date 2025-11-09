package br.com.fastDelivery.web.controller;

import br.com.fastDelivery.web.domain.dto.cliente.DadosAtualizacaoCliente;
import br.com.fastDelivery.web.domain.dto.cliente.DadosListagemClientes;
import br.com.fastDelivery.web.domain.dto.usuario.DadosAlteracaoSenha;
import br.com.fastDelivery.web.domain.dto.usuario.DadosAtualizacaoUsuario;
import br.com.fastDelivery.web.domain.dto.usuario.DadosCadastroUsuario;
import br.com.fastDelivery.web.domain.service.UsuarioService;
import br.com.fastDelivery.web.domain.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RestController
@RequestMapping("/cadastrar")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar-cliente")
    public ResponseEntity<DadosCadastroUsuario> cadastrarCliente(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioService.cadastroCliente(dadosCadastroUsuario);
        var uri = uriComponentsBuilder.path("/registrar/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroUsuario(usuario));
    }

    @PostMapping("/registrar-restaurante")
    public ResponseEntity<DadosCadastroUsuario> cadastrarRestaurante(@RequestBody @Valid DadosCadastroUsuario dadosCadastroUsuario,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        var usuario = usuarioService.cadastroRestaurante(dadosCadastroUsuario);
        var uri = uriComponentsBuilder.path("/registrar/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosCadastroUsuario(usuario));
    }

    @GetMapping("/verificar-conta")
    public ResponseEntity<String> verificarEmail(@RequestParam String codigo){
        usuarioService.verificarEmail(codigo);
        return ResponseEntity.ok("Conta verificada com sucesso.");
    }

    @PatchMapping("/atualizar-usuario/{id}")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        var usuario = usuarioService.alterarDadosUsuario(id, dadosAtualizacaoUsuario);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/desativar/{id}")
    public ResponseEntity<Void> alterarSenha(@AuthenticationPrincipal Usuario logado, @RequestBody @Valid DadosAlteracaoSenha dadosAlteracaoSenha){
        usuarioService.alterarSenha(logado, dadosAlteracaoSenha);
        return ResponseEntity.noContent().build();
    }





}
