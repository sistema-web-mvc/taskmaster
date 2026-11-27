package com.taskmaster.mvp.controller;

import com.taskmaster.mvp.model.Tarefa;
import com.taskmaster.mvp.service.TarefaService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tarefas", tarefaService.listarTodas());
        return "tarefas/lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        Optional<Tarefa> tarefaOpt = tarefaService.buscarPorId(id);
        if (tarefaOpt.isEmpty()) {
            return "redirect:/tarefas";
        }
        model.addAttribute("tarefa", tarefaOpt.get());
        return "tarefas/detalhe";
    }
}