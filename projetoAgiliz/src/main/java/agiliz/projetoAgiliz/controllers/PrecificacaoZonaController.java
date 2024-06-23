package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.PrecificacaoZonaDTO;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaResponse;
import agiliz.projetoAgiliz.models.PrecificacaoZona;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.PrecificacaoZonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/precificacaoZona")
public class PrecificacaoZonaController {

    @Autowired
    PrecificacaoZonaService precificacaoZonaService;

    @PostMapping
    ResponseEntity<MensageriaService<PrecificacaoZonaResponse>> cadastrar(
            @RequestBody @Valid PrecificacaoZonaDTO precificacaoZonaDTO) {
      return  status(HttpStatus.OK).body(new MensageriaService<PrecificacaoZonaResponse>()
                .builder()
                .mensagemCliente("Precificação de zona cadastrada com sucesso")
                .data(new PrecificacaoZonaResponse(precificacaoZonaService.cadastrar(precificacaoZonaDTO)))
                .build()
        );
    }

//    @GetMapping
//    ResponseEntity<MensageriaService<Page<PrecificacaoZonaResponse>>> listarPrecificacaoZonas(Pageable pageable) {
//        return status(HttpStatus.OK).body(new MensageriaService<Page<PrecificacaoZonaResponse>>()
//                .builder()
//                .mensagemCliente("Precificação de Zonas")
//                .datas(new PrecificacaoZonaResponse(precificacaoZonaService.listarPrecificacaoZonas(pageable)))
//                .build()
//        );
//    }

    @GetMapping("/{idPrecificacaoZona}")
    ResponseEntity<MensageriaService<PrecificacaoZonaResponse>> listarPrecificacaoZonaID (
            @PathVariable UUID idPrecificacaoZona) {
        return status(HttpStatus.OK).body(new MensageriaService<PrecificacaoZonaResponse>()
                .builder()
                .mensagemCliente("Colaborador por ID")
                .data(new PrecificacaoZonaResponse(precificacaoZonaService.listarPrecificacaoZonaID(idPrecificacaoZona)))
                .build()
        );
    }

    @PutMapping("/{idPrecificacaoZona}")
    ResponseEntity<MensageriaService<PrecificacaoZonaResponse>> alterarPrecificacaoZonaPorId(
            @PathVariable UUID idPrecificacaoZona, @RequestBody @Valid PrecificacaoZonaDTO precificacaoZonaDTO) {
        return status(HttpStatus.OK).body(new MensageriaService<PrecificacaoZonaResponse>()
                .builder()
                .mensagemCliente("Precificação de zona alterada com sucesso")
                .data(new PrecificacaoZonaResponse(precificacaoZonaService.alterarPrecificacaoZonaPorId(idPrecificacaoZona, precificacaoZonaDTO)))
                .build()
        );
    }

    @DeleteMapping("/{idPrecificacaoZona}")
    public ResponseEntity<MensageriaService<Void>> deletarPrecificacaoZonaPorId(@PathVariable UUID idPrecificacaoZona) {
        precificacaoZonaService.deletarPrecificacaoZonaPorId(idPrecificacaoZona);
        return status(HttpStatus.OK).body(new MensageriaService<Void>()
                .builder()
                .mensagemCliente("Precificação de zona excluída com sucesso")
                .status(HttpStatus.OK.value())
                .build()
        );
    }


}
