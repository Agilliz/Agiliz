package agiliz.projetoAgiliz.services;

import agiliz.projetoAgiliz.configs.security.JWT.GerenciadorTokenJWT;
import agiliz.projetoAgiliz.dto.LoginDTO;
import agiliz.projetoAgiliz.dto.UsuarioLoginDTO;
import agiliz.projetoAgiliz.models.Colaborador;
import agiliz.projetoAgiliz.repositories.IColaboradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ColaboradorServiceTest {

    @Mock
    private GerenciadorTokenJWT gerenciadorTokenJWT;

    @Mock
    private IColaboradorRepository colaboradorRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private ColaboradorService colaboradorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInserir() {
        Colaborador colaborador = new Colaborador();
        when(colaboradorRepository.save(any(Colaborador.class))).thenReturn(colaborador);

        Colaborador result = colaboradorService.inserir(colaborador);

        assertNotNull(result);
        verify(colaboradorRepository, times(1)).save(colaborador);
    }

    @Test
    public void testListarTodosPageable() {
        Page<Colaborador> page = new PageImpl<>(Arrays.asList(new Colaborador(), new Colaborador()));
        when(colaboradorRepository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<Colaborador> result = colaboradorService.listarTodos(PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(colaboradorRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    public void testListarTodos() {
        List<Colaborador> colaboradores = Arrays.asList(new Colaborador(), new Colaborador());
        when(colaboradorRepository.findAll()).thenReturn(colaboradores);

        List<Colaborador> result = colaboradorService.listarTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(colaboradorRepository, times(1)).findAll();
    }

    @Test
    public void testLogin() {
        UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
        usuarioLoginDTO.setEmail("test@example.com");
        usuarioLoginDTO.setSenha("password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmailColaborador("test@example.com");
        loginDTO.setSenhaColaborador("password");
        when(colaboradorRepository.findByEmailColaborador(anyString())).thenReturn(Optional.of(loginDTO));

        when(gerenciadorTokenJWT.generateToken(any(Authentication.class))).thenReturn("token");

        UsuarioLoginDTO result = colaboradorService.login(usuarioLoginDTO);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        assertEquals("password", result.getSenha());
        assertEquals("token", result.getToken());
    }

    @Test
    public void testDeletarPorId() throws Exception {
        UUID idColaborador = UUID.randomUUID();
        when(colaboradorRepository.findById(idColaborador)).thenReturn(Optional.of(new Colaborador()));

        colaboradorService.deletarPorId(idColaborador);

        verify(colaboradorRepository, times(1)).deleteById(idColaborador);
    }

    @Test
    public void testDeletarPorIdNotFound() {
        UUID idColaborador = UUID.randomUUID();
        when(colaboradorRepository.findById(idColaborador)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            colaboradorService.deletarPorId(idColaborador);
        });

        assertEquals("Funcionário não encontrado", exception.getMessage());
    }
}
