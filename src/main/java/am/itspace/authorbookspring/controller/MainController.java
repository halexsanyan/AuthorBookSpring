package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.dto.UserRequestDto;
import am.itspace.authorbookspring.service.EmailService;
import am.itspace.authorbookspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final UserService userService;
    private final EmailService emailService;


    @GetMapping("/")
    public String homePage(@ModelAttribute("user") UserRequestDto userRequest, ModelMap modelMap, @RequestParam(name = "msg", required = false) String msg) {

        modelMap.addAttribute("authors", userService.findAll());
        modelMap.addAttribute("msg", msg);
        return "home";
    }



    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }


}
