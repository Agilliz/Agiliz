package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.configs.security.JWT.GerenciadorTokenJWT;
import agiliz.projetoAgiliz.dto.*;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ColaboradorService {

    @Autowired
    GerenciadorTokenJWT gerenciadorTokenJWT;

    @Autowired
    private IColaboradorRepository colaboradorRepository;

    @Autowired
    private IPacoteRepository pacoteRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Colaborador inserir(ColaboradorDTO colaboradorDTO){
        Colaborador colaborador = new Colaborador();
        BeanUtils.copyProperties(colaboradorDTO, colaborador);
        criptografarSenha(colaboradorDTO.senhaColaborador(), colaborador);
        colaboradorRepository.save(colaborador);
        return colaborador;
    }

    public void criptografarSenha(String senha, Colaborador colaborador){
        String senhaCriptografada = passwordEncoder.encode(senha);
        colaborador.setSenhaColaborador(senhaCriptografada);
    }

    public Colaborador alterar(UUID idColaborador, ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = getPorId(idColaborador);
        BeanUtils.copyProperties(colaboradorDTO, colaborador);
        criptografarSenha(colaboradorDTO.senhaColaborador(), colaborador);
        return colaboradorRepository.save(colaborador);
    }

    public String listarColaboradoresComMaiorEntrega(){
        return colaboradorRepository.findColaboradorComMaisPacotes().get(0);
    }

    public String listarColaboradoresComMenorEntrega(){
        return colaboradorRepository.findColaboradorComMenosPacotes().get(0);
    }

    public TotalEntregaDTO listarTotalEntreguesETotalPacotes(){
        return colaboradorRepository.listarTotalEntreguesETotalPacotes();
    }

    public TotalEntregaDTO listarTotalEmRotaETotalPacotes(){
        return colaboradorRepository.listarTotalEmRotaETotalPacotes();
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

    public List<MesPorQtdDeEntregaDTO> listarMesPorQtdEntrega(){
        return pacoteRepository.findQtdEntregaPorMes();
    }

    public Colaborador getPorId(UUID id) {
        if(!colaboradorRepository.existsById(id)) throw new ResponseEntityException(HttpStatus.NOT_FOUND, 
        "Colaborador não encontrado",404);

        return colaboradorRepository.findById(id).get();
    }

    public Page<Colaborador> listarTodos(Pageable pageable) {
        return colaboradorRepository.findAll(pageable);
    }
    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public UsuarioLoginDTO login(UsuarioLoginDTO usuarioLoginDTO){
        try {
            final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());
            
            final Authentication authentication = this.authenticationManager.authenticate(credentials);
            
            Optional<LoginDTO> userFound = colaboradorRepository.findByEmailColaborador(usuarioLoginDTO.getEmail());
            
            if (!userFound.isPresent()) {
                throw new ResponseEntityException(HttpStatus.NOT_FOUND, 
                "Usuário não encontrado",404);
            }
    
            UsuarioLoginDTO user = new UsuarioLoginDTO();
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = gerenciadorTokenJWT.generateToken(authentication);
            
            user.setEmail(userFound.get().getEmailColaborador());
            user.setSenha(userFound.get().getSenhaColaborador());
            user.setToken(token);
            
            return user;
        } catch (Exception e) {
            throw new ResponseEntityException(HttpStatus.BAD_REQUEST, e.getMessage(),400);
        }
    }

    public void deletarPorId(UUID idColaborador) {
        if(!colaboradorRepository.existsById(idColaborador))
            throw new ResponseEntityException(HttpStatus.NOT_FOUND, "Colaborador não encontrado", 404);

        colaboradorRepository.deleteById(idColaborador);
    }

    public DashColetasDTO montarDash(){
        var dadosDash = new DashColetasDTO();
        var maiorEMenorEntrega = new MaiorEMenorEntregaDTO();

        maiorEMenorEntrega.setNomeColaboradorMaiorEntrega(listarColaboradoresComMaiorEntrega());
        maiorEMenorEntrega.setNomeColaboradorMenorEntrega(listarColaboradoresComMenorEntrega());

        dadosDash.setMaiorEMenorEntregaColaborador(maiorEMenorEntrega);
        dadosDash.setMesPorQtdDeEntrega(listarMesPorQtdEntrega());
        dadosDash.setRankingEntregas(pacoteRepository.listarRankingEntregas());
        dadosDash.setTotalAusentesECanceladas(colaboradorRepository.lisTotalAusenteECanceladas());
        dadosDash.setTotalEntregaDTO(listarTotalEntreguesETotalPacotes());
        dadosDash.setZonasAtendidas(dadosDash.getTotalEntregaDTO().getTotal());

        return dadosDash;
    }
}
