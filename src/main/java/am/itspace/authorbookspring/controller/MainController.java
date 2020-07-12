package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.model.Author;
import am.itspace.authorbookspring.repository.AuthorRepository;
import am.itspace.authorbookspring.repository.BookRepostory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

   @Value("${file.upload.dir}")
    private String uploadDir;

    private final AuthorRepository authorRepository;
    private final BookRepostory bookRepostory;


    @GetMapping("/")
    public String homePage(ModelMap modelMap,@RequestParam(name = "msg", required = false) String msg) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        modelMap.addAttribute("msg",msg);
        return "home";
    }


    @PostMapping("/saveAuthor")
    public String addAuthor(@ModelAttribute Author author, @RequestParam("image")MultipartFile file) throws IOException {
        String msg = author.getId() > 0 ? "Author was updated" : "Author was added";
        String ctrlName = author.getId() > 0 ? "/author" : "/";
        String name =System.currentTimeMillis()+"_"+file.getOriginalFilename();
        File image =new File(uploadDir);
        file.transferTo(image);
        author.setProfilePic(name);
        authorRepository.save(author);
        return "redirect:"+ctrlName+"/?msg="+msg;
    }

    @GetMapping("/author")
    public String authorPage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "author";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/author";
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
