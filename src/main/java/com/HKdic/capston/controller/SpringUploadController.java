package com.HKdic.capston.controller;
import static com.HKdic.capston.domain.DIR.*;
import static com.HKdic.capston.domain.PythonImplement.nameOfCar;

import com.HKdic.capston.domain.CarInformation;
import com.HKdic.capston.domain.PythonImplement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Controller
@RequestMapping("/movis")
public class SpringUploadController implements WebMvcConfigurer {

    private PythonImplement pythonImplement = new PythonImplement();
    private int temp = 0;

    @GetMapping
    public String movisMain() {
        return "movisMain";
    }


    @PostMapping
    public String saveFile(@RequestParam MultipartFile file, Model model) throws Exception {
        temp = 0;
        // Save File
        if (!file.isEmpty()) {
            String fullPath = FILE_SAVE_DIR.getVal() + "testFile.jpg";
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }
        CarInformation carInformation = new CarInformation();
        pythonImplement.implementML();
        System.out.println("car Name : " + nameOfCar);
        carInformation = pythonImplement.implementCrawling();
//        CarInformation carInformation = new CarInformation("제네시스 G70", "4,904 ~ 5,846만원", "SUV (중형)", "가솔린,디젤", "2151 ~ 3470cc", "8.5 ~ 13.5km/l", "5");

//        System.out.println(carInformation.toString());

//        model.addAttribute("carInfo", carInformation);
        model.addAttribute("filepath", CAR_IMAGE_DIR.getVal());
        temp = 1;
        return "redirect:/movis/result";
    }

    @GetMapping("/result")
    public String redirectionPage(){
        while(temp != 1){}

        return "movisResult";
    }

}
