package am.itspace.authorbookspring.controller;

import am.itspace.authorbookspring.dto.UserRequestDto;
import am.itspace.authorbookspring.model.Role;
import am.itspace.authorbookspring.model.User;
import am.itspace.authorbookspring.security.CurrenrUser;
import am.itspace.authorbookspring.service.EmailService;
import am.itspace.authorbookspring.service.UserService;
import am.itspace.authorbookspring.util.TextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/successLogin")
    public String successLogin(@AuthenticationPrincipal CurrenrUser currenrUser) {
        if (currenrUser == null) {
            return "redirect:/";
        }
        User user = currenrUser.getUser();
        if (user.getRole() == Role.ADMIN) {
            return "redirect:/admin";

        } else {
            return "redirect:/user";
        }
    }

    @PostMapping("/author/save")
    public String addAuthor(@Valid @ModelAttribute("user") UserRequestDto userRequest, BindingResult result,Model model,
                            @RequestParam("image") MultipartFile file) {

//        if (!TextUtil.VALID_EMAIL_ADDRESS_REGEX.matcher(userRequest.getUsername()).find()){
//            return "redirect:/?msg=Email does not valid";
//        }
        if (result.hasErrors()) {
            model.addAttribute("authors", userService.findAll());
            return "home";
        }

        if (!userRequest.getPassword().equals(userRequest.getConfirmPassword())) {
            return "redirect:/?msg=Password and Confirm does not mutch!";
        }
        Optional<User> byUsername = userService.findByUsername(userRequest.getUsername());
        if (byUsername.isPresent()) {
            return "/?msg=Author alredy exist";
        }
       String msg = userRequest.getId() > 0 ? "Author was updated" : "Author was added";
        String ctrlName = userRequest.getId() > 0 ? "/author" : "/";
        User user = User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .active(false)
                .token(UUID.randomUUID().toString())
                .build();
        userService.saveUser(user, file);
        String link = "http://localhost:8080/author/activate?email=" + userRequest.getUsername() + "&token=" + user.getToken();
        emailService.send(userRequest.getUsername(), "Welcome", "Dear " + userRequest.getName() + " You have successfully registred. " +
                "Please activate your account by clicking on:" + link);
        return "redirect:" + ctrlName + "?msg=" + msg;
    }

    @GetMapping("/author/activate")
    public String activate(@RequestParam("email") String email, @RequestParam("token") String token) {
        Optional<User> byUsername = userService.findByUsername(email);
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            if (user.getToken().equals(token)) {
                user.setActive(true);
                user.setToken("");
                userService.save(user);
                return "redirect:/?msg=User was activate, please login";
            }
        }
        return "redirect:/?msg=Something went wrong. Please try again";
    }
    @GetMapping("/author")
    public String authorPage(ModelMap modelMap, @RequestParam(name = "msg", required = false) String msg) {
        List<User> all = userService.findAll();
        modelMap.addAttribute("authors", all);
        modelMap.addAttribute("msg", msg);
        return "author";
    }

    @GetMapping("/edit/author")
    public String edit(@RequestParam("id") int id, Model model) {
        User author = userService.getOne(id);
        model.addAttribute("author", author);
        return "editAuthor";
    }

    @GetMapping("/delete/author")
    public String deleteAuthor(@RequestParam("id") int id) {
        userService.deleteAuthor(id);
        return "redirect:/author";
    }

}
