package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.adapters.PagamentoAdapter;
import agiliz.projetoAgiliz.dto.PagamentoDTO;
import agiliz.projetoAgiliz.utils.FolhaDePagamento;
import agiliz.projetoAgiliz.models.Pagamento;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.services.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<MensageriaService<Pagamento>> cadastrar(@RequestBody @Valid PagamentoDTO dto){
        return ResponseEntity.ok().body(
                new MensageriaService<>(
                        "Inserido com sucesso",
                        pagamentoService.cadastrar(dto)
                )
        );
    }

    @GetMapping
    public ResponseEntity<MensageriaService<List<PagamentoAdapter>>> get(){
        List<Pagamento> pagamentos = pagamentoService.listar();

        if(pagamentos.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.ok().body(
          new MensageriaService<List<PagamentoAdapter>>(
                  "Pagamentos",
                  serializarListaPagamentos(pagamentos),
                  HttpStatus.OK.value()
          )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<Pagamento>> getPorId(@PathVariable UUID id){
        return ResponseEntity.ok()
                .body(
                        new MensageriaService<>(
                                "Pagamento",
                                pagamentoService.listarPorId(id),
                                HttpStatus.OK.value()
                        )
                );
    }

    @GetMapping("/folha-pagamento")
    public ResponseEntity<MensageriaService<FolhaDePagamento>> getFolhaPagamento(){
        return ResponseEntity.ok().body(
                new MensageriaService<>(
                        "Folha de pagamento",
                        pagamentoService.gerarFolhaDePagamento()
                )
        );
    }

    private List<PagamentoAdapter> serializarListaPagamentos(List<Pagamento> pagamentos){
        return pagamentos.stream()
                .map(this::serializarPagamento)
                .toList();
    }

    private PagamentoAdapter serializarPagamento(Pagamento pagamento){
        return new PagamentoAdapter(pagamento);
    }
}
