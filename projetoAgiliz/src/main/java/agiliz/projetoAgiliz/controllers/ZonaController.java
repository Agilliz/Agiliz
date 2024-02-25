package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.ZonaDTO;
import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.repositories.IZonaRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;

import java.util.UUID;
import java.util.List;

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


    @PutMapping
    public String alterar(@RequestBody ZonaModel zona) {
        repository.save(zona);
        return "PEREÇa";
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
