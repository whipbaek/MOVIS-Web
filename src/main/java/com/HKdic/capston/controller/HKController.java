package com.HKdic.capston.controller;

import com.HKdic.capston.domain.ProcessBuilderTest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/HKDic")
public class HKController {

    @GetMapping
    public String PyTest(Model model) throws Exception {
        model.addAttribute("pyData", new ProcessBuilderTest().ImplPB());
        return "HKdic/PyTest";
    }
}
