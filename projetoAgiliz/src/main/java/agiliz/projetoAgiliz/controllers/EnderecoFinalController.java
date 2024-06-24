package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.EnderecoFinalDTO;
import agiliz.projetoAgiliz.dto.EnderecoFinalResponse;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaDTO;
import agiliz.projetoAgiliz.dto.PrecificacaoZonaResponse;
import agiliz.projetoAgiliz.models.EnderecoFinal;
import agiliz.projetoAgiliz.repositories.IEnderecoFinalRepository;
import agiliz.projetoAgiliz.services.EnderecoFinalService;
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

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/endereco")
@CrossOrigin
public class EnderecoFinalController {

    @Autowired
    private EnderecoFinalService enderecoFinalService;

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<EnderecoFinalResponse>> cadastrar(
            @RequestBody @Valid EnderecoFinalDTO enderecoFinalDTO){
        return status(HttpStatus.OK).body(new MensageriaService<EnderecoFinalResponse>()
                .mensagemCliente("Endereço Cadastrado com Sucesso")
                .data(new EnderecoFinalResponse(enderecoFinalService.cadastrar(enderecoFinalDTO)))
        );
    }



    @GetMapping("/{idEnderecoFinal}")
    ResponseEntity<MensageriaService<EnderecoFinalResponse>> listarEnderecoFinalID (
            @PathVariable UUID idEnderecoFinal) {
        return status(HttpStatus.OK).body(new MensageriaService<EnderecoFinalResponse>()
                .mensagemCliente("Endereco Final por ID")
                .data(new EnderecoFinalResponse(enderecoFinalService.listarEnderecoFinalID(idEnderecoFinal)))
        );
    }

    @PutMapping("/{idEnderecoFinal}")
    ResponseEntity<MensageriaService<EnderecoFinalResponse>> alterarEnderecoFinalPorId(
            @PathVariable UUID idEnderecoFinal, @RequestBody @Valid EnderecoFinalDTO enderecoFinalDTO) {
        return status(HttpStatus.OK).body(new MensageriaService<EnderecoFinalResponse>()
                .mensagemCliente("Endereco Final alterado com sucesso")
                .data(new EnderecoFinalResponse(enderecoFinalService.alterarEnderecoFinalPorId(idEnderecoFinal, enderecoFinalDTO)))
        );
    }

    @DeleteMapping("/{idEnderecoFinal}")
    public ResponseEntity<MensageriaService<Void>> deletarEnderecoFinalPorId(@PathVariable UUID idEnderecoFinal) {
        enderecoFinalService.deletarEnderecoFinalPorId(idEnderecoFinal);
        return status(HttpStatus.OK).body(new MensageriaService<Void>()
                .mensagemCliente("Endereco Final excluído com sucesso")
                .status(HttpStatus.OK.value())
        );
    }
}
