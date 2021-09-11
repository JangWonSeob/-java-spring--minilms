package ko.co.dongyang.study.minilms.user.controller;

import ko.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    @GetMapping("/user/register")
    public String register() {
        return "user/register";
    }

    @GetMapping("/user/register-complete")
    public String registerComplete() {
        return "user/register_complete";

    }

}
