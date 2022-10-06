package hello.upload.controller;
import com.HKdic.capston.domain.PBClass_Gram;
import com.HKdic.capston.domain.PBClass_Home;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam MultipartFile file, Model model) throws Exception {
        if (!file.isEmpty()) {
            String fullPath = fileDir + "testFile.jpg";
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }

        // Thread.sleep(10000); // 사진 저장되는데 걸리는 시간

//        model.addAttribute("str", new PBClass_Home().ImplementPython());
        model.addAttribute("str", new PBClass_Gram().ImplementPython());


        return "testform";
    }
}
