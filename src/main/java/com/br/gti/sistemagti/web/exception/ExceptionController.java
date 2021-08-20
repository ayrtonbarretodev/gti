package com.br.gti.sistemagti.web.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice //serve como um ouvinte dentro do aplicação, quando uma regra for verdadeira ele é chamado
public class ExceptionController {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView usuarioNaoEncontradoException(UsernameNotFoundException ex){
        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 404);
        model.addObject("error", "Operação não pode ser realizada.");
        model.addObject("message", ex.getMessage());
        return model;
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ModelAndView EntidadeJaExistenteException(SQLIntegrityConstraintViolationException ex){
        ModelAndView model = new ModelAndView("error");
        model.addObject("status", 500);
        model.addObject("error", "A Entidade que você seja adicionar já existe no banco.");
        model.addObject("message", ex.getMessage());
        return model;
    }
}
