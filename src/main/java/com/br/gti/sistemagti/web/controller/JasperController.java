package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.repository.CategoriaRepository;
import com.br.gti.sistemagti.repository.DepartamentoRepository;
import com.br.gti.sistemagti.service.JasperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class JasperController {

    @Autowired
    private JasperService service;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/relatorio/pdf/jr1")
    public void exibirRelatorioTotal(@RequestParam ("code") String code,
                                     @RequestParam ("acao") String acao,
                                     HttpServletResponse response) throws IOException {
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        if (acao.equals("v")){
            response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf" );
        }else{
            response.setHeader("Content-disposition", "attachment; filename=relatorio-"+ code + ".pdf" );
        }
        response.getOutputStream().write(bytes);
    }

    @GetMapping("/relatorio/pdf/jr2/{code}")
    public void exibirRelatorioPorCategoria(@PathVariable("code") String code,
                                     @RequestParam (name = "categoria", required = false) String categoria,
                                     HttpServletResponse response) throws IOException {

        service.addParams("CATEGORIA_DESC", categoria.isEmpty() ? null : categoria);
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf" );
        response.getOutputStream().write(bytes);
    }

    @ModelAttribute("categorias")
    public List<String> getCategorias(){
        return categoriaRepository.findCategorias();
    }

    @ModelAttribute("departamentos")
    public List<String> getDepartamentos(){
        return departamentoRepository.findDepartamentos();
    }
}
