package com.HKdic.capston.controller;
import static com.HKdic.capston.domain.DIR.*;
import static com.HKdic.capston.domain.PythonImplement.nameOfCars;
import static com.HKdic.capston.domain.PythonImplement.percentages;

import com.HKdic.capston.domain.CarInformation;
import com.HKdic.capston.domain.PythonImplement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/movis")
public class SpringUploadController implements WebMvcConfigurer {

    private final PythonImplement pythonImplement = new PythonImplement();
    private int temp = 0;
    public static ArrayList<CarInformation> carInformations = new ArrayList<>();

    @GetMapping
    public String movisMain() {
        temp = 0;
        return "movisMain";
    }


    @PostMapping
    public String saveFile(@RequestParam MultipartFile file) throws Exception {
        temp = 0;
        // Save File
        if (!file.isEmpty()) {
            String fullPath = FILE_SAVE_DIR.getVal() + "testFile.jpg";
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }
        pythonImplement.implementML();

        log.info("");
        log.info("크롤링 시작");
        log.info("");
        pythonImplement.implementCrawling();

        log.info("크롤링 끝");

        temp = 1;
        return "redirect:/movis/redir";
    }

    @GetMapping("/redir")
    public String redirectionPage(Model model){
        while(temp != 1){}

        model.addAttribute("carInfos", carInformations);
        model.addAttribute("filepath", CAR_IMAGE_DIR.getVal());
        model.addAttribute("percentages", percentages);

        return "movisSelect";
    }

    @PostMapping("/redir")
    public String testMapping(@RequestParam("index") int index, Model model) {

        int firstIndex;
        int secondIndex;
        if(index == 0){
            firstIndex = 1;
            secondIndex= 2;
        } else if(index == 1){
            firstIndex = 0;
            secondIndex = 2;
        } else{
            firstIndex = 0;
            secondIndex = 1;
        }

        model.addAttribute("carInfo", carInformations.get(index));
        model.addAttribute("carInfos", carInformations);
        model.addAttribute("index", index);
        model.addAttribute("firstIndex", firstIndex);
        model.addAttribute("secondIndex", secondIndex);
        model.addAttribute("firstCarInfo", carInformations.get(firstIndex));
        model.addAttribute("secondCarInfo", carInformations.get(secondIndex));

        return "movisResult";
    }


}
