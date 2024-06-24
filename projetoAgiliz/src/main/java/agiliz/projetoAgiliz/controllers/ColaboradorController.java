package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.ColaboradorDTO;
import agiliz.projetoAgiliz.dto.ColaboradorResponse;
import agiliz.projetoAgiliz.dto.MatrizColaboradorDTO;
import agiliz.projetoAgiliz.dto.UsuarioLoginDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.services.ColaboradorService;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.utils.CalculadoraDatas;
import agiliz.projetoAgiliz.utils.GeradorArquivo;
import agiliz.projetoAgiliz.utils.ListaObj;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/funcionario")
@CrossOrigin
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

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
            @RequestBody @Valid ColaboradorDTO colaboradorDTO) {
            return status(HttpStatus.OK).body(
                    new MensageriaService<ColaboradorResponse>()
                            .mensagemCliente("Colaborador cadastrado com sucesso")
                            .data(new ColaboradorResponse(colaboradorService.inserir(colaboradorDTO)))
            );
    }

    @PutMapping("/alterar/{idColaborador}")
    public ResponseEntity<MensageriaService<ColaboradorResponse>> alterarColaboradorById(
            @PathVariable UUID idColaborador,
            @Valid @RequestBody ColaboradorDTO colaboradorDTO) {
               return status(HttpStatus.OK)
                       .body(
                               new MensageriaService<ColaboradorResponse>()
                                       .mensagemCliente("Colaborador alterado com sucesso")
                                       .data(new ColaboradorResponse(colaboradorService.alterar(idColaborador, colaboradorDTO)))
                                       .status(200)
                       );
    }

    @DeleteMapping("/{idColaborador}")
    public ResponseEntity<MensageriaService<Void>> deleteColaboradorById(@PathVariable UUID idColaborador) {
        colaboradorService.deletarPorId(idColaborador);
        return status(204)
                .body(
                    new MensageriaService<Void>()
                            .mensagemCliente("eletado com sucesso")
                            .status(204)
                );
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
    ResponseEntity<MensageriaService<Page<Colaborador>>> listarColaborador(Pageable pageable) {
        Page<Colaborador> colaboradores = colaboradorService.listarTodos(pageable);

        if (!colaboradores.isEmpty()) {
            return status(HttpStatus.OK)
                    .body(
                            new MensageriaService<Page<Colaborador>>()
                                    .mensagemCliente("Funcionários")
                                    .data(colaboradores)
                                    .status(204)
                    );
        }

        return status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idFuncionario}")
    ResponseEntity<MensageriaService<Colaborador>> listarFuncionariosPorId(@PathVariable UUID idFuncionario) {
        return status(HttpStatus.OK)
                .body(
                        new MensageriaService<Colaborador>()
                                .mensagemCliente("Funcionário")
                                .data(colaboradorService.getPorId(idFuncionario))
                                .status(200)
                );
    }

    @GetMapping(value = "/gravar-arquivo", produces = "text/csv")
    public ResponseEntity<MensageriaService<byte[]>> gravarArquivo() throws Exception {
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
