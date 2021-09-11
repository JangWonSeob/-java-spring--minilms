package ko.co.dongyang.study.minilms.user.controller;

import ko.co.dongyang.study.minilms.common.model.ResponseResult;
import ko.co.dongyang.study.minilms.common.model.ResponseResultHeader;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import ko.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class ApiUserController {

    private final UserService userService;


    @PostMapping("/api/user/register.api")
    public ResponseEntity<?> register(@RequestBody UserRegister parameter) {


        ServiceResult result = userService.addUser(parameter);

        if (!result.isResult()) {
            return ResponseResult.fail(result.getMessage());
        }

        return ResponseResult.success(parameter);


    }

}
