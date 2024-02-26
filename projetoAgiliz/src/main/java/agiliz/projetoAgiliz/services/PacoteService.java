package agiliz.projetoAgiliz.services;

import org.springframework.beans.factory.annotation.Autowired;

import agiliz.projetoAgiliz.models.PacoteModel;
import agiliz.projetoAgiliz.models.ZonaModel;
import agiliz.projetoAgiliz.repositories.IPacoteRepository;
import agiliz.projetoAgiliz.repositories.IZonaRepository;

public class PacoteService {

    @Autowired
    private IZonaRepository zonaRepository;

    public void associarZonaAoPacote(PacoteModel pacote) {
        ZonaModel zona = zonaRepository
                .findByLimiteInferiorGreaterThanEqualAndLimiteSuperiorLessThanEqual();

    }
}
