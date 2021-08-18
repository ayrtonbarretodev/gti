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

@Controller
public class JasperController {

    @Autowired
    private JasperService service;

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

    @GetMapping("/relatorio/pdf/jr3/{code}")
    public void exibirRelatorioPorDepartamento(@PathVariable("code") String code,
                                            @RequestParam (name = "departamento", required = false) String departamento,
                                            HttpServletResponse response) throws IOException {

        service.addParams("DEPARTAMENTO_DESC", departamento.isEmpty() ? null : departamento);
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf" );
        response.getOutputStream().write(bytes);
    }


    @GetMapping("/relatorio/pdf/jr4/{code}")
    public void exibirRelatorioPorCategoriaDepartamento(@PathVariable("code") String code,
                                               @RequestParam (name = "departamento", required = false) String departamento,
                                               @RequestParam (name = "categoria", required = false) String categoria,
                                               HttpServletResponse response) throws IOException {

        service.addParams("DEPARTAMENTO_DESC", departamento.isEmpty() ? null : departamento);
        service.addParams("CATEGORIA_DESC", categoria.isEmpty() ? null : categoria);
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf" );
        response.getOutputStream().write(bytes);
    }


    @GetMapping("/relatorio/pdf/jr6/{code}")
    public void exibirRelatorioPorStatus(@PathVariable("code") String code,
                                               @RequestParam (name = "status", required = false) String status,
                                               HttpServletResponse response) throws IOException {

        service.addParams("STATUS_DESC", status.isEmpty() ? null : status);
        byte[] bytes = service.exportarPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader("Content-disposition", "inline; filename=relatorio-"+ code + ".pdf" );
        response.getOutputStream().write(bytes);
    }

}
