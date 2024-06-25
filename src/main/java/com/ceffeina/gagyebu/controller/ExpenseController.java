package com.ceffeina.gagyebu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ceffeina.gagyebu.dto.ExpenseDTO;
import com.ceffeina.gagyebu.dto.PageRequestDTO;
import com.ceffeina.gagyebu.service.ExpenseService;

@Controller
@RequestMapping("/expense")
@Log4j2
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/expense/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list: " + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register() {
    }

    @PostMapping("/register")
    public String registerPost(ExpenseDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto... " + dto);
        Long eno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", eno);
        return "redirect:/expense/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam("eno") Long eno,
                     @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                     Model model) {
        log.info("eno: " + eno);
        ExpenseDTO dto = service.read(eno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("eno") Long eno, RedirectAttributes redirectAttributes) {
        log.info("eno: " + eno);
        service.remove(eno);
        redirectAttributes.addFlashAttribute("msg", eno);
        return "redirect:/expense/list";
    }

    @PostMapping("/modify")
    public String modify(ExpenseDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("dto: " + dto);
        service.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("eno", dto.getEno());
        return "redirect:/expense/read";
    }
}
