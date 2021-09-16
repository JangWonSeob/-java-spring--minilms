package ko.co.dongyang.study.minilms.user.service;

import ko.co.dongyang.study.minilms.user.dto.UserDto;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import org.springframework.security.core.userdetails.UserDetailsService;

// UserDetailsServices는 SecurityConfiguration 관련 설정
public interface UserService extends UserDetailsService {

    ServiceResult addUser(UserRegister parameter);

    UserDto getUser(String userId);
}
