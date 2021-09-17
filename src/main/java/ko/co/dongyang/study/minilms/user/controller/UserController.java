package ko.co.dongyang.study.minilms.user.controller;

import ko.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/user/register")
    public String register() {
        return "user/register";
    }

    @GetMapping("/user/register-complete")
    public String registerComplete() {
        return "user/register_complete";
    }

    @GetMapping("/user/mypage")
    public String myage() {
        return "user/mypage";
    }

    // GetMapping 와 PostMapping 둘 다 처리를 위해서
    @RequestMapping("/user/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("user/reset/password/{uuid}")
    String resetPassword(@PathVariable("uuid")String uuid){



        return null;
    }

}
