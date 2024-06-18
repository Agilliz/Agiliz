package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.dto.ColaboradorDTO;
import agiliz.projetoAgiliz.dto.MatrizColaboradorDTO;
import agiliz.projetoAgiliz.dto.UsuarioLoginDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.services.ColaboradorService;
import agiliz.projetoAgiliz.services.MensageriaService;
import agiliz.projetoAgiliz.utils.CalculadoraDatas;
import agiliz.projetoAgiliz.utils.GeradorArquivo;
import agiliz.projetoAgiliz.utils.ListaObj;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/funcionario")
@CrossOrigin
public class ColaboradorController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ColaboradorService colaboradorService;

    @PostMapping("/login")
    ResponseEntity<UsuarioLoginDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        var userLogin = colaboradorService.login(usuarioLoginDTO);

        if (!userLogin.equals(null)) {
            return ResponseEntity.status(HttpStatus.OK).body(userLogin);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/cadastrar")
    ResponseEntity<MensageriaService<Colaborador>> cadastroColaborador(
            @RequestBody @Valid ColaboradorDTO colaboradorDTO) {
        
        Colaborador colaborador = new Colaborador();

        String senhaCriptografada = passwordEncoder.encode(colaboradorDTO.senhaColaborador());

          BeanUtils.copyProperties(colaboradorDTO, colaborador);

        colaborador.setSenhaColaborador(senhaCriptografada);
        
    
        try {
            MensageriaService mensageriaService = new MensageriaService<>(
                    "Funcionario Cadastrado com Sucesso",
                    colaboradorService.inserir(colaborador), 200);
            return ResponseEntity.status(HttpStatus.CREATED).body(mensageriaService);
        } catch (Exception e) {
            MensageriaService mensageriaService = new MensageriaService(e, 500);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensageriaService);
        }
    }

    @DeleteMapping("/deletar/{idColaborador}")
    public ResponseEntity deleteColaboradorById(@PathVariable UUID idColaborador) throws Exception{
        colaboradorService.deletarPorId(idColaborador);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/matriz-colaborador")
    public ResponseEntity<List<String[]>> getMatrizColaboradorPorDespesa() {
        return ResponseEntity.status(HttpStatus.OK)
        .body(colaboradorService.listarMatriz());
    }
    

    @GetMapping("/")
    ResponseEntity<MensageriaService<Page<Colaborador>>> listarColaborador(Pageable pageable) {
        Page<Colaborador> colaboradores = colaboradorService.listarTodos(pageable);
        System.out.println(colaboradores);

        if (!colaboradores.isEmpty()) {
            MensageriaService mensageriaService = new MensageriaService("Funcionarios", colaboradores, 200);
            return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
        }
        MensageriaService mensageriaService = new MensageriaService("Não há funcionários", 204);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    // @GetMapping("/{idFuncionario}")
    // ResponseEntity<MensageriaService<List<Colaborador>>>
    // listarFuncionariosPorId(@PathVariable UUID idFuncionario){
    // Optional<Colaborador> colaboradorOpt =
    // colaboradorService.listarTodos(idFuncionario);
    //
    // if (colaboradorOpt.isPresent()){
    // MensageriaService mensageriaService = new MensageriaService<>("Funcionario",
    // funcionarioId, 200);
    // return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
    // }
    // MensageriaService mensageriaService = new MensageriaService("Funcionário não
    // encontrado",404);
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    // }

    // @DeleteMapping("/{idFuncionario}")
    // ResponseEntity<MensageriaService<List<Colaborador>>> deletarPorId
    // (@PathVariable UUID idFuncionario) {
    // Optional<Colaborador> funcionarioList =
    // colaboradorRepository.findById(idFuncionario);
    //
    // if (funcionarioList.isPresent()){
    // colaboradorRepository.deleteById(idFuncionario);
    // MensageriaService mensageriaService = new MensageriaService("Funcionário
    // excluído com sucesso", 200);
    // return ResponseEntity.status(HttpStatus.OK).body(mensageriaService);
    // }
    // MensageriaService mensageriaService = new MensageriaService("Funcionário não
    // encontrado", funcionarioList, 404);
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensageriaService);
    //
    // }

    @GetMapping("/teste-chave")
    public Date getData() {
        LocalDate dataChave = LocalDate.of(2024, 8, 10);
        return CalculadoraDatas.calcularQuintoDiaUtil(dataChave);
    }

    @GetMapping(value = "/gravar-arquivo", produces = "text/csv")
    public ResponseEntity<byte[]> gravarArquivo() throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body(GeradorArquivo.gravarArquivo(colaboradorService.listarTodos(), "Arquivo dos colaboradores"));
        }catch (Exception e){
            System.out.println(e);
            throw new Exception(e);
        }

    }

    @GetMapping("/ler-arquivo")
    public void lerArquivo(){
        GeradorArquivo.leArquivoCsv("Arquivo dos colaboradores");
    }
}
