package agiliz.projetoAgiliz.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import agiliz.projetoAgiliz.dto.UnidadeDTO;
import agiliz.projetoAgiliz.models.Unidade;
import agiliz.projetoAgiliz.repositories.IUnidadeRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/unidade")
@CrossOrigin
public class UnidadeController {

    @Autowired
    IUnidadeRepository unidadeRepository;

    @GetMapping("/")
    public ResponseEntity<MensageriaService<Page<Unidade>>> listarUnidades(Pageable pageable) {
        Page<Unidade> unidadeList = unidadeRepository.findAll(pageable);

        if (!unidadeList.isEmpty()) {
            //UnidadeService.removeFornecedorListUnidade(unidadeList);
            MensageriaService mensageriaService = new MensageriaService("Unidades", unidadeList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idUnidade}")
    public ResponseEntity<MensageriaService<Unidade>> listarUnidadePorId(@PathVariable UUID idUnidade) {
        Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);

        if (unidade.isPresent()) {
            MensageriaService mensageriaService = new MensageriaService("Unidade", unidade, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Unidade não encontrada", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<MensageriaService<Unidade>> cadastrarUnidade(@RequestBody @Valid UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        BeanUtils.copyProperties(unidadeDTO, unidade);

        try {
            unidadeRepository.save(unidade);

            MensageriaService mensageriaService = new MensageriaService("Unidade cadastrada com sucesso",
                    unidade, 201);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);

        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @PutMapping("alterar/{idUnidade}")
    public ResponseEntity<MensageriaService<Unidade>> alterarUnidadePorId(@PathVariable UUID idUnidade,
                                                                          @RequestBody @Valid UnidadeDTO unidadeDTO) {
        
        Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);

        if(unidade.isPresent()){
            Unidade unidadeModel = new Unidade();

            BeanUtils.copyProperties(unidadeDTO, unidadeModel);
            
            try {
                unidadeRepository.save(unidadeModel);

                MensageriaService mensageriaService = new MensageriaService<>("Unidade atualizada com sucesso ", unidadeModel, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);

            } catch (Exception e) {
                MensageriaService mensageriaService = new MensageriaService<>("Ocorreu um erro a atualizar a unidade ", 400);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
            }

        }

        MensageriaService mensageriaService = new MensageriaService<>("Unidade não encontrada ", 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/deletar/{idUnidade}")
    public ResponseEntity<MensageriaService<Unidade>> deletarUnidade(@PathVariable UUID idUnidade){
        Optional<Unidade> unidade = unidadeRepository.findById(idUnidade);

        if(unidade.isPresent()){
            try {
                unidadeRepository.deleteById(idUnidade);

                MensageriaService mensageriaService = new MensageriaService("Unidade deletada com sucesso ", 
                                                                            unidade, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);                                                                

            } catch (Exception e) {
                MensageriaService mensageriaService = new MensageriaService("Ocorreu um erro ao deletar a unidade ", 
                                                                            unidade, 400);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);                                                                
            }
        }

        MensageriaService mensageriaService = new MensageriaService("Unidade não encontrada ", 
                                                                            unidade, 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);                                                                
    }

}
