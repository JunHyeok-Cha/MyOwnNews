package project.MyOwnNews.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    public String create(@ModelAttribute UserDto userDto){
        System.out.println("received username = " + userDto.getUsername());
        System.out.println("received password = " + userDto.getPassword());
        System.out.println("received nickname = " + userDto.getNickname());
        userService.join(userDto);
        return "redirect:/";
    }

}
