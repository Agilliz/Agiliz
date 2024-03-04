package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.MultaDTO;
import agiliz.projetoAgiliz.models.MultaModel;
import agiliz.projetoAgiliz.repositories.IMultaRepository;
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
@RequestMapping("/multa")
public class MultaController {

    @Autowired
    IMultaRepository multaRepository;


    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<MultaModel>> cadastrarMultas(@RequestBody @Valid MultaDTO multaDTO){
        var multa = new MultaModel();
        BeanUtils.copyProperties(multaDTO, multa);
        try {
            MensageriaService mensageriaService = new MensageriaService(
                    "Multa cadastrada com sucesso",
                    multaRepository.save(multa), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }


    @GetMapping
    ResponseEntity<MensageriaService<List<MultaModel>>> listarMultas () {
        List<MultaModel> multasList = multaRepository.findAll();

        if (!multasList.isEmpty()){
            MensageriaService mensageriaService = new MensageriaService("Multas", multasList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há multas", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensageriaService);
    }


    @GetMapping("/{idMulta}")
    ResponseEntity<MensageriaService<MultaModel>> listarMultasPorId (@PathVariable UUID idMulta) {
        Optional<MultaModel> multasList = multaRepository.findById(idMulta);

        if (multasList.isPresent()){
            MensageriaService mensageriaService = new MensageriaService("Multa", multasList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Multa não encontrada", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/{idMulta}")
    ResponseEntity<MensageriaService<MultaModel>> deletarMultaPorId (@PathVariable UUID idMulta) {
        Optional<MultaModel> multasList = multaRepository.findById(idMulta);

        if (multasList.isPresent()){
            multaRepository.deleteById(idMulta);
            MensageriaService mensageriaService = new MensageriaService("Multa excluída com sucesso", multasList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Multa não encontrada", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }
}
