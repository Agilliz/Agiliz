package agiliz.projetoAgiliz.controllers;


import agiliz.projetoAgiliz.dto.ColaboradorDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.services.AgendaDeTarefasService;
import agiliz.projetoAgiliz.services.ColaboradorService;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/funcionario")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @Autowired
    private AgendaDeTarefasService agenda;


    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<Colaborador>> cadastroColaborador(@RequestBody @Valid ColaboradorDTO colaboradorDTO){
        try {
            MensageriaService mensageriaService = new MensageriaService<>(
                    "Funcionario Cadastrado com Sucesso",
                    colaboradorService.inserir(colaboradorDTO), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @GetMapping("/")
    ResponseEntity<MensageriaService<Page<Colaborador>>> listarColaborador(Pageable pageable) {
        Page<Colaborador> colaboradores = colaboradorService.listarTodos(pageable);

        if (!colaboradores.isEmpty()) {
            MensageriaService mensageriaService = new MensageriaService("Funcionarios", colaboradores, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há funcionários", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
//    @GetMapping("/{idFuncionario}")
//    ResponseEntity<MensageriaService<List<Colaborador>>> listarFuncionariosPorId(@PathVariable UUID idFuncionario){
//        Optional<Colaborador> colaboradorOpt = colaboradorService.listarTodos(idFuncionario);
//
//        if (colaboradorOpt.isPresent()){
//            MensageriaService mensageriaService = new MensageriaService<>("Funcionario", funcionarioId, 200);
//            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
//        }
//        MensageriaService mensageriaService = new MensageriaService("Funcionário não encontrado",404);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
//    }

//    @DeleteMapping("/{idFuncionario}")
//    ResponseEntity<MensageriaService<List<Colaborador>>> deletarPorId (@PathVariable UUID idFuncionario) {
//        Optional<Colaborador> funcionarioList = colaboradorRepository.findById(idFuncionario);
//
//        if (funcionarioList.isPresent()){
//            colaboradorRepository.deleteById(idFuncionario);
//            MensageriaService mensageriaService = new MensageriaService("Funcionário excluído com sucesso", 200);
//            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
//        }
//        MensageriaService mensageriaService = new MensageriaService("Funcionário não encontrado", funcionarioList, 404);
//        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
//
//    }

    @GetMapping("/teste-chave")
    public Date getData(){
        LocalDate dataChave = LocalDate.of(2024, 8, 10);
        return agenda.getQuintoDiaUtil(dataChave);
    }
}
