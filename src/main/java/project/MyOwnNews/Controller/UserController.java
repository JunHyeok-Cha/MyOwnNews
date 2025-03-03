package project.MyOwnNews.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.MyOwnNews.Service.UserService;
import project.MyOwnNews.dto.UserDto;
import project.MyOwnNews.entity.User;


@Controller
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/users/new")
    public String createForm(){
        return "users/new";
    }
    @PostMapping("/users/new")
    public String create(@ModelAttribute UserDto userDto, Model model){
        try{
            userService.join(userDto);
            return "redirect:/";
        }
        catch(IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/users/new";
        }
    }
    @GetMapping("/users/login")
    public String loginForm(){
        return "users/login";
    }
    @PostMapping("/users/login")
    public String login(@ModelAttribute UserDto userDto, Model model, HttpSession session){
        try{
            userService.check(userDto);
            session.setAttribute("loggedUser", userDto.getUsername());  //세션에 username 저장
            return "redirect:/";
        }
        catch(IllegalArgumentException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/users/login";
        }
    }
    @GetMapping("/users/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
