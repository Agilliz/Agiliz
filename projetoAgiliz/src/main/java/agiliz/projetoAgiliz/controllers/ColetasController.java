package agiliz.projetoAgiliz.controllers;

import agiliz.projetoAgiliz.services.ColetasService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ColetasController {
    private final ColetasService service;



}
