package ko.co.dongyang.study.minilms.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("user/register")
    public String register() {
        return "user/register";
    }

}
