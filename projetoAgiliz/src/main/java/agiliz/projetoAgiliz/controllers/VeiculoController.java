package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.VeiculoDTO;
import agiliz.projetoAgiliz.models.VeiculoModel;
import agiliz.projetoAgiliz.repositories.IVeiculoRepository;
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
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    IVeiculoRepository veiculoRepository;

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<VeiculoModel>> cadastrarVeiculo(@RequestBody @Valid VeiculoDTO veiculoDTO){
        var veiculo = new VeiculoModel();
        BeanUtils.copyProperties(veiculoDTO, veiculo);
        try {
            MensageriaService mensageriaService = new MensageriaService(
                    "Multa cadastrada com sucesso",
                    veiculoRepository.save(veiculo), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e){
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @GetMapping("/")
    ResponseEntity<MensageriaService<Page<VeiculoModel>>> listarVeiculos (Pageable pageable) {
        Page<VeiculoModel> veiculosList = veiculoRepository.findAll(pageable);

        if (!veiculosList.isEmpty()){
            MensageriaService mensageriaService = new MensageriaService("Veículos", veiculosList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há veículos", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensageriaService);
    }

    @GetMapping("/{idVeiculo}")
    ResponseEntity<MensageriaService<VeiculoModel>> listarVeiculosPorId (@PathVariable UUID idMulta) {
        Optional<VeiculoModel> veiculoList = veiculoRepository.findById(idMulta);

        if (veiculoList.isPresent()){
            MensageriaService mensageriaService = new MensageriaService("Veiculo", veiculoList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Veículo não encontrado", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/{idVeiculo}")
    ResponseEntity<MensageriaService<VeiculoModel>> deletarVeiculosPorId (@PathVariable UUID idVeiculo) {
        Optional<VeiculoModel> multasList = veiculoRepository.findById(idVeiculo);

        if (multasList.isPresent()){
            veiculoRepository.deleteById(idVeiculo);
            MensageriaService mensageriaService = new MensageriaService("Veículo excluído com sucesso", multasList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Veículo não encontrado", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }
}
