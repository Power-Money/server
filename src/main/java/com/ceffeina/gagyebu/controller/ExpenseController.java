package com.ceffeina.gagyebu.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("pageRequestDTO", pageRequestDTO);  // PageRequestDTO를 모델에 추가
        model.addAttribute("pageResultDTO", service.getList(pageRequestDTO)); // PageResultDTO를 모델에 추가
        return "list";  // templates/list.html 파일을 참조
    }

    @GetMapping("/register")
    public void register(){
    }

    @PostMapping("/register")
    public String registerPost(ExpenseDTO dto, RedirectAttributes redirectAttributes) {
        Long eno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", eno + " 등록되었습니다.");
        return "redirect:/expense/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long eno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        ExpenseDTO dto = service.read(eno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long eno, RedirectAttributes redirectAttributes) {
        service.remove(eno);
        redirectAttributes.addFlashAttribute("msg", eno + " 삭제되었습니다.");
        return "redirect:/expense/list";
    }

    @PostMapping("/modify")
    public String modify(ExpenseDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
        service.modify(dto);
        redirectAttributes.addFlashAttribute("msg", dto.getEno() + " 수정되었습니다.");
        return "redirect:/expense/list";
    }
}
