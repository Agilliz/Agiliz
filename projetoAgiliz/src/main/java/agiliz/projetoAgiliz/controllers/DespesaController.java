package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.DespesaDTO;
import agiliz.projetoAgiliz.models.Despesa;
import agiliz.projetoAgiliz.services.DespesaService;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/despesa")
@CrossOrigin
public class DespesaController {

    @Autowired
    private DespesaService despesaService;


    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<Despesa>> cadastrarDespesa(@RequestBody @Valid DespesaDTO despesaDTO){

        try {
            MensageriaService mensageriaService = new MensageriaService<>(
                    "Despesa foi cadastrada com sucesso",
                    despesaService.inserir(despesaDTO), 200);
                    return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e){
            MensageriaService mensageriaService = new MensageriaService<>(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

//    @GetMapping("/")
//    ResponseEntity<MensageriaService<Page<Despesa>>> listarDespesa(Pageable pageable){
//        Page<Despesa> despesas = despesaService.listarDespesas(pageable);
//
//        if (!despesas.isEmpty()) {
//            MensageriaService mensageriaService = new MensageriaService(" Despesas", despesas, 200);
//            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
//        }
//        MensageriaService mensageriaService = new MensageriaService("Não há nenhuma despesa", 404);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }



}
