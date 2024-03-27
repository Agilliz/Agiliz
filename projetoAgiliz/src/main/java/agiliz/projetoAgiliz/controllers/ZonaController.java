package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.ZonaDTO;
import agiliz.projetoAgiliz.models.Zona;
import agiliz.projetoAgiliz.repositories.IZonaRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;

import java.util.UUID;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<MensageriaService<Zona>> cadastrar(@RequestBody @Valid ZonaDTO zonaDTO) {
        var zona = new Zona();
        BeanUtils.copyProperties(zonaDTO, zona);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new MensageriaService<Zona>(
                "Zona cadastrada com sucesso",
                repository.save(zona),
                HttpStatus.CREATED.value()
            ));
    }

    @GetMapping
    public ResponseEntity<MensageriaService<Page<Zona>>> listar(Pageable pageable) {
        Page<Zona> zonas = repository.findAll(pageable);
        
        if(zonas.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        return ResponseEntity.status(HttpStatus.OK)
            .body(new MensageriaService<Page<Zona>>(
                "Zonas:",
                zonas,
                HttpStatus.OK.value()
            ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensageriaService<Zona>> listarPorId(@PathVariable UUID id){
        Optional<Zona> zonaOpt = repository.findById(id);

        if(zonaOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MensageriaService<Zona>(
                    "Zona: ",
                    zonaOpt.get(),
                    HttpStatus.OK.value()
                ));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MensageriaService<Zona>> alterar(@RequestBody @Valid ZonaDTO zonaDTO, @PathVariable UUID id) {
        var zonaOpt = repository.findById(id);

        if(zonaOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        var zona = zonaOpt.get();
        BeanUtils.copyProperties(zonaDTO, zona);

        return ResponseEntity.status(HttpStatus.OK)
            .body(new MensageriaService<Zona>(
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
