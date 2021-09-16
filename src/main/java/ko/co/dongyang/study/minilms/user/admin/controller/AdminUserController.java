package ko.co.dongyang.study.minilms.user.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

    @GetMapping("/admin/user/list")
    public String userList() {
        return "/admin/user/list";
    }

}
