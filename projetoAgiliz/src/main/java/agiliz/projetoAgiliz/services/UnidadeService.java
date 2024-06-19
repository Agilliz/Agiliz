package agiliz.projetoAgiliz.services;


import agiliz.projetoAgiliz.models.Unidade;
import agiliz.projetoAgiliz.repositories.IUnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UnidadeService {

   private final IUnidadeRepository repository;

   public Unidade getUnidadePorId(UUID idUnidade){
       var unidadeOpt = repository.findById(idUnidade);
       if(unidadeOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       return unidadeOpt.get();
   }

   public void contabilizarRetornoTotal(Unidade unidade, double precoPacote){
       unidade.incrementarRetorno(precoPacote);
       repository.save(unidade);
   }

    public LocalTime getHorarioCorteMedia() {
       Double horaMediaSegundos = repository.findAVGHorarioCorte();
       if(horaMediaSegundos == null) return LocalTime.of(12, 0);
       return LocalTime.ofSecondOfDay(horaMediaSegundos.longValue());
    }

   public String getNomeUnidadeMaiorRetorno() {
       return repository.findUnidadeMaiorRetorno();
   }

   public String getNomeUnidadeMenorRetorno() {
       return repository.findUnidadeMenorRetorno();
   }
}
