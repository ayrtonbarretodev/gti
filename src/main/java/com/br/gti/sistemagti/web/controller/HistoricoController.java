package com.br.gti.sistemagti.web.controller;

import com.br.gti.sistemagti.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HistoricoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping("/historico")
    public String PaginaHistorico(){
        return "historico/historico";
    }

    @GetMapping("/buscar/equipamentos")
    public ModelAndView buscarEquipamentoPorMac(@RequestParam("mac") String mac){
        return new ModelAndView("historico/historico", "equipamentos",
                equipamentoRepository.findEquipamentoByEnderecoMacOrTomboPatrimonialOrNome(mac));
    }
}
