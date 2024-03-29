package com.br.gti.sistemagti.web.conversor;

import com.br.gti.sistemagti.domain.Categoria;
import com.br.gti.sistemagti.service.CategoriaNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategoriaConverter implements Converter<String, Categoria> {

    @Autowired
    private CategoriaNewService categoriaNewService;

    @Override
    public Categoria convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return categoriaNewService.buscarPorId(id);
    }
}
