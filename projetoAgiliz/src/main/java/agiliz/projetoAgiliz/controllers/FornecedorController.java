package agiliz.projetoAgiliz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import agiliz.projetoAgiliz.models.FornecedorModel;
import agiliz.projetoAgiliz.repositories.IFornecedorRepository;
import agiliz.projetoAgiliz.services.MensageriaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    IFornecedorRepository fornecedorRepository;

    @GetMapping("/")
    public ResponseEntity<MensageriaService<List<FornecedorModel>>> listarFornecedores() {
        List<FornecedorModel> fornecedorList = fornecedorRepository.findAll();

        if (!fornecedorList.isEmpty()) {
            MensageriaService<List<FornecedorModel>> mensageriaService = new MensageriaService<>
            ("Fornecedores:", fornecedorList, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }

        MensageriaService<List<FornecedorModel>> mensageriaService = new MensageriaService<>
                                                ("Fornecedores", "No content", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensageriaService);
    }

}
