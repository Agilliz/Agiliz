package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.FuncionarioDTO;
import agiliz.projetoAgiliz.dto.MotoristaDaVezDTO;
import agiliz.projetoAgiliz.models.FuncionarioModel;
import agiliz.projetoAgiliz.models.MotoristaDaVezModel;
import agiliz.projetoAgiliz.repositories.IMotoristaDaVezRepository;
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
@RequestMapping("/motorista")
public class MotoristaDaVezController {

    @Autowired
    IMotoristaDaVezRepository motoristaDaVezRepository;

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<MotoristaDaVezModel>> cadastroMotorista(@RequestBody @Valid MotoristaDaVezDTO motoristaDaVezDTO){
        var motoristaVez = new MotoristaDaVezModel();
        BeanUtils.copyProperties(motoristaDaVezDTO, motoristaVez);
        try {
            MensageriaService mensageriaService = new MensageriaService<>(
                    "Motorista cadastrado com Sucesso",
                    motoristaDaVezRepository.save(motoristaVez), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e){
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @GetMapping("/")
    ResponseEntity<MensageriaService<List<MotoristaDaVezModel>>> listarMotoristas() {
        List<MotoristaDaVezModel> motoristaList = motoristaDaVezRepository.findAll();

        if (!motoristaList.isEmpty()){
            MensageriaService mensageriaService = new MensageriaService("Motoristas", motoristaList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há motoristas", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idMotoristaVez}")
    ResponseEntity<MensageriaService<List<MotoristaDaVezModel>>> listarMotoristaPorId(@PathVariable UUID idMotorista) {
        Optional<MotoristaDaVezModel> motoristaList = motoristaDaVezRepository.findById(idMotorista);

        if (motoristaList.isPresent()){
            MensageriaService mensageriaService = new MensageriaService("Motorista", motoristaList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService<>("Motorista não encontrado", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{idMotoristaVez}")
    ResponseEntity<MensageriaService<List<MotoristaDaVezModel>>> deletarMotoristaPorId(@PathVariable UUID idMotorista){
        Optional<MotoristaDaVezModel> motoristaList = motoristaDaVezRepository.findById(idMotorista);

        if (motoristaList.isPresent()){
            motoristaDaVezRepository.deleteById(idMotorista);
            MensageriaService mensageriaService = new MensageriaService("Motorista excluido com sucesso", 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Motorista não encontrado", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
