package ko.co.dongyang.study.minilms.user.service;

import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;

public interface UserService {

    ServiceResult addUser(UserRegister parameter);
}
