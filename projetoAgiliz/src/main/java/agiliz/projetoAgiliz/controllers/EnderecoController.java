package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.EnderecoFinalDTO;
import agiliz.projetoAgiliz.models.EnderecoFinal;
import agiliz.projetoAgiliz.repositories.IEnderecoFinalRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private IEnderecoFinalRepository enderecoFinalRepository;

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<EnderecoFinal>> cadastroFuncionario(@RequestBody @Valid EnderecoFinalDTO enderecoFinalDTO){
        var enderecoFinal = new EnderecoFinal();
        BeanUtils.copyProperties(enderecoFinalDTO, enderecoFinal);
        try {
            MensageriaService mensageriaService = new MensageriaService<>(
                    "Endereço Cadastrado com Sucesso",
                    enderecoFinalRepository.save(enderecoFinal), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        }catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @GetMapping("/")
    ResponseEntity<MensageriaService<List<EnderecoFinal>>> listarEnderecos() {
        List<EnderecoFinal> enderecosFinaisList = enderecoFinalRepository.findAll();
        if (!enderecosFinaisList.isEmpty()) {
            MensageriaService mensageriaService = new MensageriaService("Endereço", enderecosFinaisList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há endereços", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{idEnderecoFinal}")
    ResponseEntity<MensageriaService<List<EnderecoFinal>>> listarEnderecoFinalPorId(@PathVariable UUID idEnderecoFinal){
        Optional<EnderecoFinal> enderecoFinalId = enderecoFinalRepository.findById(idEnderecoFinal);

        if (enderecoFinalId.isPresent()){
            MensageriaService mensageriaService = new MensageriaService<>("Endereço", enderecoFinalId, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Endereço não encontrado",404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/{idEnderecoFinal}")
    ResponseEntity<MensageriaService<List<EnderecoFinal>>> deletarPorId (@PathVariable UUID idEnderecoFinal) {
        Optional<EnderecoFinal> enderecosFinaisList = enderecoFinalRepository.findById(idEnderecoFinal);

        if (enderecosFinaisList.isPresent()){
            enderecoFinalRepository.deleteById(idEnderecoFinal);
            MensageriaService mensageriaService = new MensageriaService("Endereço excluído com sucesso", 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Funcionário não encontrado", enderecosFinaisList, 404);
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);

    }
}
