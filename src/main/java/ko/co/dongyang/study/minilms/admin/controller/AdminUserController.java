package ko.co.dongyang.study.minilms.admin.controller;

import ko.co.dongyang.study.minilms.user.dto.UserDto;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserSearch;
import ko.co.dongyang.study.minilms.user.service.UserService;
import ko.co.dongyang.study.minilms.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminUserController {

    private final UserService userService;

    @GetMapping("/admin/user/list")
    public String userList(Model model, HttpServletRequest request, UserSearch parameter) {

        System.out.println(parameter);
        System.out.println(parameter.getQueryString());

        ServiceResult result = userService.getUsers(parameter);


        if (result.isResult()) {
            int totalCount = result.getTotalCount();

            List<UserDto> userList = result.getList();
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("userList", userList);


            PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageIndex(), parameter.getQueryString());
            String pagerHtml = pageUtil.pager();

            model.addAttribute("pager", pagerHtml);

        }

        return "/admin/user/list";
    }

    @GetMapping("/admin/user/detail")
    String userDetail(Model model, UserSearch parameter) {

        ServiceResult<UserDto> result = userService.getDetail(parameter);

        if (result.isFail()) {
            //TODO: 에러 처리

        }

        model.addAttribute("user", result.getDetail());

        return "/admin/user/detail";
    }

    @PostMapping("/admin/user/delete")
    String userDelete(Model model, UserSearch parameter){

        ServiceResult<UserDto> result = userService.delete(parameter.getUserId());

        return "redirect:/admin/user/list";
    }

}
