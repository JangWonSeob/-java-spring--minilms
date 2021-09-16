package ko.co.dongyang.study.minilms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "main/index";
    }

    @GetMapping("/common/denied")
    public String commonDenied() {
        return "/common/denied";
    }

}
