package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.ZonaDTO;
import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.repositories.IZonaRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zona")
public class ZonaController {
    @Autowired
    private IZonaRepository repository;
    
    @PostMapping
    public ResponseEntity<MensageriaService<ZonaModel>> cadastrar(@RequestBody @Valid ZonaDTO zonaDTO) {
        var zona = new ZonaModel();
        BeanUtils.copyProperties(zonaDTO, zona);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new MensageriaService<ZonaModel>(
                "Zona cadastrada com sucesso",
                repository.save(zona),
                HttpStatus.CREATED.value()
            ));
    }

    @GetMapping
    public ResponseEntity<MensageriaService<List<ZonaModel>>> listar() {
        List<ZonaModel> zonas = repository.findAll();
        
        if(zonas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new MensageriaService<>(
                    "Nenhuma zonas encontrada",
                    "No content",
                    HttpStatus.NO_CONTENT.value()
                ));
        }


        return ResponseEntity.status(HttpStatus.OK)
            .body(new MensageriaService<List<ZonaModel>>(
                "Zonas:",
                zonas,
                HttpStatus.OK.value()
            ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<ZonaModel>> listarPorId(@PathVariable UUID id){
        Optional<ZonaModel> zonaOpt = repository.findById(id);

        if(zonaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensageriaService<>(
                    "Zona não encontrada",
                    "No content",
                    HttpStatus.NOT_FOUND.value()
                ));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MensageriaService<ZonaModel>(
                    "Zona: ",
                    zonaOpt.get(),
                    HttpStatus.OK.value()
                ));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MensageriaService<ZonaModel>> alterar(@RequestBody @Valid ZonaDTO zonaDTO, @PathVariable UUID id) {
        var zonaOpt = repository.findById(id);

        if(zonaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensageriaService<>(
                    "Zona não encontrada",
                    "No content",
                    HttpStatus.NOT_FOUND.value()
                ));
        }

        var zona = zonaOpt.get();
        BeanUtils.copyProperties(zonaDTO, zona);

        return ResponseEntity.status(HttpStatus.OK)
            .body(new MensageriaService<ZonaModel>(
                "Zona atualizada com sucesso",
                repository.save(zona),
                HttpStatus.OK.value()
            ));
    }

    @DeleteMapping("/{id}")
    public <T> ResponseEntity<MensageriaService<T>> deletar(@PathVariable UUID id) {
        var zonaOpt = repository.findById(id);

        if(zonaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MensageriaService<T>(
                    "Zona não encontrada",
                    "No content",
                    HttpStatus.NOT_FOUND.value()
                ));
        }

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body(new MensageriaService<>(
                "Zona deletada com sucesso",
                HttpStatus.ACCEPTED.value()
            ));
    }
}
