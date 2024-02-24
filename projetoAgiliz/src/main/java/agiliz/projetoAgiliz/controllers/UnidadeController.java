package agiliz.projetoAgiliz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.dto.UnidadeDTO;
import agiliz.projetoAgiliz.models.UnidadeModel;
import agiliz.projetoAgiliz.repositories.IUnidadeRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/unidade")
public class UnidadeController {

    @Autowired
    IUnidadeRepository unidadeRepository;

    @GetMapping("/")
    public ResponseEntity<MensageriaService<List<UnidadeModel>>> listarUnidades() {
        List<UnidadeModel> unidadeList = unidadeRepository.findAll();

        if (!unidadeList.isEmpty()) {
            MensageriaService mensageriaService = new MensageriaService("Unidades", unidadeList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{idUnidade}")
    public ResponseEntity<MensageriaService<UnidadeModel>> listarUnidadePorId(@PathVariable UUID idUnidade) {
        Optional<UnidadeModel> unidade = unidadeRepository.findById(idUnidade);

        if(unidade.isPresent()){
            MensageriaService mensageriaService = new MensageriaService("Unidade", unidade, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService mensageriaService = new MensageriaService("Unidade não encontrada", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MensageriaService<UnidadeModel>> cadastrarUnidade(@RequestBody @Valid UnidadeDTO unidadeDTO){
        UnidadeModel unidadeModel = new UnidadeModel();

        BeanUtils.copyProperties(unidadeDTO, unidadeModel);

        try {
            unidadeRepository.save(unidadeModel);

            MensageriaService mensageriaService = new MensageriaService("Unidade cadastrada com sucesso", 
                                                unidadeModel, 201);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);

        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

}
