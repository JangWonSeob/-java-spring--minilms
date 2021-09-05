package ko.co.dongyang.study.minilms.user.controller;

import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import ko.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/user/register")
    public String registerSubmit(Model model, HttpServletRequest request
            , UserRegister parameter){

        System.out.println(parameter.toString());

        ServiceResult result = userService.addUser(parameter);

        if(!result.isResult()){
            model.addAttribute("errorMessage", result.getMessage());
            System.out.println("errorMessage : "+ result.getMessage());
            return "user/register";
        }
        // 성공일때 처리는 나중에

        return "redirect:/user/register-complete";

    }

    @GetMapping("/user/register-complete")
    public String registerComplete() {

        return "user/register_complete";

    }

}
