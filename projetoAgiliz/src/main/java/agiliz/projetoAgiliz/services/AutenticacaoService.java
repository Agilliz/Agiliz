package agiliz.projetoAgiliz.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import agiliz.projetoAgiliz.dto.LoginDTO;
import agiliz.projetoAgiliz.dto.UserDetailsDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    IColaboradorRepository colaboradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<LoginDTO> user = colaboradorRepository.findByEmailColaborador(username);
        

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuário %s não encontrado", username));
        }

        return new UserDetailsDTO(user.get().getEmailColaborador(), user.get().getSenhaColaborador());
    }

    public void criar(Colaborador usuarioLoginDTO) {
        final Colaborador novoColaborador = colaboradorRepository.save(usuarioLoginDTO);
    }

}
