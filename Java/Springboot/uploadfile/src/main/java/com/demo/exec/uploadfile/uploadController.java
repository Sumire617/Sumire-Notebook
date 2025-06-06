package com.demo.exec.uploadfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class uploadController {
    // 要上传的文件夹路径
    private static final String UPLOADED_FOLDER = "E://temp//";
    // 默认上传路径页面
    @GetMapping("/")
    public String index() {
        // 返回upload.html页面
        return "upload";
    }
    // 上传文件
    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        // RedirectAttributes是一个接口,用于重定向时传递参数
        // MultipartFile是一个接口,用于上传文件
        if (file.isEmpty()) {
            // 用RedirectAttributes的addFlashAttribute方法,传递临时参数,在重定向后,参数会被清空
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the file and save it somewhere
            // getBytes获取文件内容
            byte[] bytes = file.getBytes();
            // 获取路径
            Path dir = Paths.get(UPLOADED_FOLDER);
            // 保存文件路径,获取上传文件的原始文件名
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            // 没有文件夹则创建文件夹
            if(!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Server throw IOException");
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
