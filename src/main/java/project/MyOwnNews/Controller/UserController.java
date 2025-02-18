package project.MyOwnNews.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String create(UserDto userDto){
        User user = new User();
        userService.join(userDto);
        return "redirect:/";
    }

}
