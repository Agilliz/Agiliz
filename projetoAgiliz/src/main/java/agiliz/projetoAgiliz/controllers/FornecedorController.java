package agiliz.projetoAgiliz.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import agiliz.projetoAgiliz.dto.FornecedorDTO;
import agiliz.projetoAgiliz.models.FornecedorModel;
import agiliz.projetoAgiliz.repositories.IFornecedorRepository;
import agiliz.projetoAgiliz.repositories.IFornecedorRepository;
import agiliz.projetoAgiliz.services.MensageriaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    IFornecedorRepository fornecedorRepository;

    @GetMapping("/")
    public ResponseEntity<MensageriaService<List<FornecedorModel>>> listarFornecedores() {

        List<FornecedorModel> fornecedorList = fornecedorRepository.findAll();

        if (!fornecedorList.isEmpty()) {
            MensageriaService<List<FornecedorModel>> mensageriaService = new MensageriaService<>("Fornecedores:",
                    fornecedorList, 200);

            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService<List<FornecedorModel>> mensageriaService = new MensageriaService<>(
                "Nenhum fornecedor encontrado", "No content", 204);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{idFornecedor}")
    public ResponseEntity<MensageriaService<FornecedorModel>> listarFornecedorPorId(@PathVariable UUID idFornecedor) {
        Optional<FornecedorModel> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent() /* Só valida se o optional não ta null */) {
            MensageriaService<FornecedorModel> mensageriaService = new MensageriaService("Fornecedor", fornecedor, 200);

            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService<FornecedorModel> mensageriaService = new MensageriaService<>("Fornecedor não encontrado",
                404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MensageriaService<FornecedorModel>> cadastrarFornecedor(
            @RequestBody @Valid FornecedorDTO fornecedorDTO) {

        FornecedorModel fornecedorModel = new FornecedorModel();
        BeanUtils.copyProperties(fornecedorDTO, fornecedorModel);

        try {
            fornecedorRepository.save(fornecedorModel);
        } catch (Exception e) {
            MensageriaService<FornecedorModel> mensageriaService = new MensageriaService(
                    "Ocorreu um erro a cadastrar o usuáriofornecedor", fornecedorModel, 400);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
        }
        MensageriaService<FornecedorModel> mensageriaService = new MensageriaService<FornecedorModel>(
                "Fornecedor cadastrado com sucesso",
                fornecedorModel, 201);

        System.out.println(mensageriaService);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
    }

    @PutMapping("alterar/{idFornecedor}")
    public ResponseEntity<MensageriaService<FornecedorModel>> alterarFornecedor(@PathVariable UUID idFornecedor,
            @RequestBody @Valid FornecedorDTO fornecedorDTO) {

        FornecedorModel fornecedorModel = new FornecedorModel();
        Optional fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            BeanUtils.copyProperties(fornecedorDTO, fornecedorModel);

            try {

                fornecedorRepository.save(fornecedorModel);
                MensageriaService<FornecedorModel> mensageriaService = new MensageriaService(
                        "Fornecedor atualizado com sucesso", fornecedorModel, 200);

                return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);

            } catch (Exception e) {
                MensageriaService<FornecedorModel> mensageriaService = new MensageriaService(
                        "Ocorreu um erro ao atualizar o fornecedor", fornecedorModel, 400);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensageriaService);
            }
        }

        MensageriaService<FornecedorModel> mensageriaService = new MensageriaService(
                "Fornecedor não encontrado", fornecedorModel, 404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    }

    @DeleteMapping("/deletar/{idFornecedor}")
    public ResponseEntity<MensageriaService<FornecedorModel>> deletarFornecedor(@PathVariable UUID idFornecedor) {

        Optional<FornecedorModel> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            try {
                fornecedorRepository.delete(fornecedor.get());
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
