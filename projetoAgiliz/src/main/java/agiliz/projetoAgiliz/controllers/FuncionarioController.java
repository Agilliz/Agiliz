package agiliz.projetoAgiliz.controllers;


import agiliz.projetoAgiliz.dto.FuncionarioDTO;
import agiliz.projetoAgiliz.models.FuncionarioModel;
import agiliz.projetoAgiliz.repositories.IFuncionarioRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<FuncionarioModel>> cadastroFuncionario(@RequestBody @Valid FuncionarioDTO funcionarioDTO){
        var funcionario = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDTO, funcionario);
        try {
            MensageriaService mensageriaService = new MensageriaService<>(
                    "Funcionario Cadastrado com Sucesso",
                    funcionarioRepository.save(funcionario), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @GetMapping("/")
    ResponseEntity<MensageriaService<Page<FuncionarioModel>>> listarFuncionarios(Pageable pageable) {
        Page<FuncionarioModel> funcionarioList = funcionarioRepository.findAll(pageable);
        if (!funcionarioList.isEmpty()) {
            MensageriaService mensageriaService = new MensageriaService("Funcionarios", funcionarioList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há funcionários", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{idFuncionario}")
    ResponseEntity<MensageriaService<List<FuncionarioModel>>> listarFuncionariosPorId(@PathVariable UUID idFuncionario){
        Optional<FuncionarioModel> funcionarioId = funcionarioRepository.findById(idFuncionario);

        if (funcionarioId.isPresent()){
            MensageriaService mensageriaService = new MensageriaService<>("Funcionario", funcionarioId, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Funcionário não encontrado",404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/{idFuncionario}")
    ResponseEntity<MensageriaService<List<FuncionarioModel>>> deletarPorId (@PathVariable UUID idFuncionario) {
        Optional<FuncionarioModel> funcionarioList = funcionarioRepository.findById(idFuncionario);

        if (funcionarioList.isPresent()){
            funcionarioRepository.deleteById(idFuncionario);
            MensageriaService mensageriaService = new MensageriaService("Funcionário excluído com sucesso", 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Funcionário não encontrado", funcionarioList, 404);
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);

    }
}
