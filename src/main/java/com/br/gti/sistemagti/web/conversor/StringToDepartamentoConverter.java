package com.br.gti.sistemagti.web.conversor;

import com.br.gti.sistemagti.domain.Departamento;
import com.br.gti.sistemagti.service.DepService;
//import com.br.gti.sistemagti.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

//    @Autowired
//    private DepartamentoService service;

    @Autowired
    private DepService depService;

    @Override
    public Departamento convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return depService.buscarPorId(id);
    }
}
