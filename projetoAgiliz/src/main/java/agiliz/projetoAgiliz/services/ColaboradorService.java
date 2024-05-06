package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.JWT.GerenciadorTokenJWT;
import agiliz.projetoAgiliz.dto.ColaboradorDTO;
import agiliz.projetoAgiliz.dto.LoginDTO;
import agiliz.projetoAgiliz.dto.UsuarioLoginDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
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

    @Autowired
    private AgendaDeTarefasService agenda;

    public Colaborador inserir(Colaborador colaborador){
        colaboradorRepository.save(colaborador);
        return colaborador;
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
