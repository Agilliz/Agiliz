package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.JWT.GerenciadorTokenJWT;
import agiliz.projetoAgiliz.dto.LoginDTO;
import agiliz.projetoAgiliz.dto.MatrizColaboradorDTO;
import agiliz.projetoAgiliz.dto.UsuarioLoginDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ColaboradorService {

    @Autowired
    GerenciadorTokenJWT gerenciadorTokenJWT;

    @Autowired
    private IColaboradorRepository colaboradorRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    public Colaborador inserir(Colaborador colaborador){
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    public List<String[]> listarMatriz() {
        List<MatrizColaboradorDTO> lista = colaboradorRepository.listarMatriz();
        
        // Agrupar por CPF
        Map<String, List<Double>> cpfToValoresMap = new HashMap<>();
        for (MatrizColaboradorDTO colaborador : lista) {
            cpfToValoresMap
                .computeIfAbsent(colaborador.getCpf(), k -> new ArrayList<>())
                .add(colaborador.getValor());
        }

        // Converter para matriz
        List<String[]> matriz = new ArrayList<>();
        for (Map.Entry<String, List<Double>> entry : cpfToValoresMap.entrySet()) {
            String cpf = entry.getKey();
            List<Double> valores = entry.getValue();
            String[] linha = new String[valores.size() + 1];
            linha[0] = cpf;
            for (int i = 0; i < valores.size(); i++) {
                linha[i + 1] = String.valueOf(valores.get(i));
            }
            matriz.add(linha);
        }

        return matriz;
    }

    public Colaborador getPorId(UUID id) {
        if(!colaboradorRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return colaboradorRepository.findById(id).get();
    }

    public Page<Colaborador> listarTodos(Pageable pageable) {
        return colaboradorRepository.findAll(pageable);
    }
    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);
        
        Optional<LoginDTO> userFound = colaboradorRepository.findByEmailColaborador(usuarioLoginDTO.getEmail());

        UsuarioLoginDTO user = new UsuarioLoginDTO();


        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJWT.generateToken(authentication);


        user.setEmail(userFound.get().getEmailColaborador());
        user.setSenha(userFound.get().getSenhaColaborador());
        user.setToken(token);

        return user;
    }

    public void deletarPorId(UUID idColaborador) throws Exception{
        if(colaboradorRepository.findById(idColaborador).isPresent()){
            colaboradorRepository.deleteById(idColaborador);
            return;
        }
        throw new Exception("Funcionário não encontrado");
    }
}
