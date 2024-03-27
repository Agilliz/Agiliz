package agiliz.projetoAgiliz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.dto.FornecedorDTO;
import agiliz.projetoAgiliz.models.Fornecedor;
import agiliz.projetoAgiliz.repositories.IFornecedorRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    IFornecedorRepository fornecedorRepository;

    @GetMapping("/")
    public ResponseEntity<MensageriaService<Page<Fornecedor>>> listarFornecedores(Pageable pageable) {

        Page<Fornecedor> fornecedorList = fornecedorRepository.findAll(pageable);

        if (!fornecedorList.isEmpty()) {
            MensageriaService<Page<Fornecedor>> mensageriaService = new MensageriaService("Fornecedores:",
                    fornecedorList, 200);

            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService<List<Fornecedor>> mensageriaService = new MensageriaService<>(
                "Nenhum fornecedor encontrado", "No content", 204);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idFornecedor}")
    public ResponseEntity<MensageriaService<Fornecedor>> listarFornecedorPorId(@PathVariable UUID idFornecedor) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent() /* Só valida se o optional não ta null */) {
            MensageriaService<Fornecedor> mensageriaService = new MensageriaService("Fornecedor", fornecedor, 200);

            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService<Fornecedor> mensageriaService = new MensageriaService<>("Fornecedor não encontrado",
                404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MensageriaService<Fornecedor>> cadastrarFornecedor(
            @RequestBody @Valid FornecedorDTO fornecedorDTO) {

        Fornecedor fornecedor = new Fornecedor();
        BeanUtils.copyProperties(fornecedorDTO, fornecedor);

        try {
            fornecedorRepository.save(fornecedor);
        } catch (Exception e) {
            MensageriaService<Fornecedor> mensageriaService = new MensageriaService(
                    "Ocorreu um erro a cadastrar o usuáriofornecedor", fornecedor, 400);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
        }
        MensageriaService<Fornecedor> mensageriaService = new MensageriaService<Fornecedor>(
                "Fornecedor cadastrado com sucesso",
                fornecedor, 201);

        System.out.println(mensageriaService);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
    }

    @PutMapping("alterar/{idFornecedor}")
    public ResponseEntity<MensageriaService<Fornecedor>> alterarFornecedor(@PathVariable UUID idFornecedor,
                                                                           @RequestBody @Valid FornecedorDTO fornecedorDTO) {

        Fornecedor fornecedorModel = new Fornecedor();
        Optional fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            BeanUtils.copyProperties(fornecedorDTO, fornecedorModel);

            try {

                fornecedorRepository.save(fornecedorModel);
                MensageriaService<Fornecedor> mensageriaService = new MensageriaService(
                        "Fornecedor atualizado com sucesso", fornecedorModel, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);

            } catch (Exception e) {
                MensageriaService<Fornecedor> mensageriaService = new MensageriaService(
                        "Ocorreu um erro ao atualizar o fornecedor", fornecedorModel, 400);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
            }
        }

        MensageriaService<Fornecedor> mensageriaService = new MensageriaService(
                "Fornecedor não encontrado", fornecedorModel, 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/deletar/{idFornecedor}")
    public ResponseEntity<MensageriaService<Fornecedor>> deletarFornecedor(@PathVariable UUID idFornecedor) {

        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            try {
                fornecedorRepository.delete(fornecedor.get());
                
                MensageriaService mensageriaService = new MensageriaService("Fornecedor deletado com sucesso", 
                fornecedor, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);

            } catch (Exception e) {
                MensageriaService mensageriaService = new MensageriaService("Não foi possível deletar o fornecedor",
                        fornecedor, 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
            }
        }

        MensageriaService mensageriaService = new MensageriaService("Fornecedor não encontrado",
                fornecedor, 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);

    }


}
