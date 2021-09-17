package ko.co.dongyang.study.minilms.user.service;

import ko.co.dongyang.study.minilms.user.dto.UserDto;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import ko.co.dongyang.study.minilms.user.model.UserSearch;
import org.springframework.security.core.userdetails.UserDetailsService;

// UserDetailsServices는 SecurityConfiguration 관련 설정
public interface UserService extends UserDetailsService {

    ServiceResult addUser(UserRegister parameter);

    UserDto getUser(String userId);

    ServiceResult<UserDto> getUsers(UserSearch parameter);

    ServiceResult<UserDto> getDetail(UserSearch parameter);

    ServiceResult<UserDto> delete(String parameter);

    ServiceResult<UserDto> reqResetPassword(String userId);
}
