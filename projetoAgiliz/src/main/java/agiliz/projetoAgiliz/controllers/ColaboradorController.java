package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.colaborador.*;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.services.ColaboradorService;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.utils.GeradorArquivo;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping("/email-recuperacao-senha")
    public ResponseEntity<Void> solicitarAlteracaoSenha(
            @RequestBody @Valid EmailAlterarSenhaRequest dto
    ) {
        colaboradorService.mandarEmailAlteracaoSenha(dto.email());
        return noContent().build();
    }

    @PatchMapping("/alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid AlterarSenhaRequest dto) {
        colaboradorService.alterarSenha(dto);
        return noContent().build();
    }

    @PostMapping("/login")
    ResponseEntity<UsuarioLoginDTO> login(@RequestBody @Valid UsuarioLoginDTO usuarioLoginDTO) {
        var userLogin = colaboradorService.login(usuarioLoginDTO);

        if (!userLogin.equals(null)) {
            return status(HttpStatus.OK).body(userLogin);
        }

        return status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<ColaboradorResponse>> cadastroColaborador(
            @RequestBody @Valid ColaboradorRequest colaboradorRequest) {
            return status(HttpStatus.OK).body(
                    new MensageriaService<ColaboradorResponse>()
                            .mensagemCliente("Colaborador cadastrado com sucesso")
                            .data(new ColaboradorResponse(colaboradorService.inserir(colaboradorRequest)))
            );
    }

    @PutMapping("/alterar/{idColaborador}")
    public ResponseEntity<MensageriaService<ColaboradorResponse>> alterarColaboradorById(
            @PathVariable UUID idColaborador,
            @Valid @RequestBody ColaboradorRequest colaboradorRequest) {
               return status(HttpStatus.OK)
                       .body(
                               new MensageriaService<ColaboradorResponse>()
                                       .mensagemCliente("Colaborador alterado com sucesso")
                                       .data(new ColaboradorResponse(colaboradorService.alterar(idColaborador, colaboradorRequest)))
                                       .status(200)
                       );
    }

    @DeleteMapping("/{idColaborador}")
    public ResponseEntity<MensageriaService<Void>> deleteColaboradorById(@PathVariable UUID idColaborador) {
        colaboradorService.deletarPorId(idColaborador);
        return status(204).build();
    }

    @GetMapping("/matriz-colaborador")
    public ResponseEntity<MensageriaService<List<String[]>>> getMatrizColaboradorPorDespesa() {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<List<String[]>>()
                                .mensagemCliente("Matriz colaborador por CPF")
                                .data(colaboradorService.listarMatriz())
                                .status(200)
                );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<Page<Colaborador>>> listarColaborador(Pageable pageable) {
        Page<Colaborador> colaboradores = colaboradorService.listarTodos(pageable);

        if (!colaboradores.isEmpty()) {
            return status(HttpStatus.OK)
                    .body(
                            new MensageriaService<Page<Colaborador>>()
                                    .mensagemCliente("Funcionários")
                                    .data(colaboradores)
                                    .status(200)
                    );
        }

        return status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idFuncionario}")
    public ResponseEntity<MensageriaService<Colaborador>> listarFuncionariosPorId(@PathVariable UUID idFuncionario) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<Colaborador>()
                                .mensagemCliente("Funcionário")
                                .data(colaboradorService.getPorId(idFuncionario))
                                .status(200)
                );
    }

    @GetMapping(value = "/gravar-arquivo", produces = "text/csv")
    public ResponseEntity<MensageriaService<byte[]>> gravarArquivo() {
        try {
            return status(HttpStatus.OK)
                    .body(
                            new MensageriaService<byte[]>()
                                    .mensagemCliente("CSV colaboradores")
                                    .data(GeradorArquivo.gravarArquivo(colaboradorService.listarTodos(), "Arquivo dos colaboradores"))
                                    .status(200)
                    );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
