package com.HKdic.capston.controller;
import com.HKdic.capston.domain.CarInformation;
import com.HKdic.capston.domain.PythonImplement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController implements WebMvcConfigurer {
    @Value("${file.dir}")
    private String fileDir;



    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam MultipartFile file, Model model) throws Exception {

        // Save File
        if (!file.isEmpty()) {
            String fullPath = fileDir + "testFile.jpg";
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }

        // Logic implement (ML & Crawling)
        PythonImplement pythonImplement = new PythonImplement();
        pythonImplement.implementML();
        CarInformation carInformation = pythonImplement.implementCrawling();

        carInformation = new CarInformation("제네시스 G70", "4,904 ~ 5,846만원", "SUV (중형)", "가솔린,디젤", "2151 ~ 3470cc", "8.5 ~ 13.5km/l", "5");

        model.addAttribute("carInfo", carInformation);
        model.addAttribute("filepath", "C:/Users/jibae/Projects/capston/images/car.png");

        return "testform";
    }
}
