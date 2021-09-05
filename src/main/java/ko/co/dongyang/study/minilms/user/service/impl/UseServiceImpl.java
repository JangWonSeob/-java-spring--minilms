package ko.co.dongyang.study.minilms.user.service.impl;

import ko.co.dongyang.study.minilms.user.entity.User;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import ko.co.dongyang.study.minilms.user.repository.UserRepository;
import ko.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UseServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ServiceResult addUser(UserRegister parameter) {

        ServiceResult result = new ServiceResult();

        if(parameter.getUserId() == null || (parameter.getUserId().length()) < 1){
            result.setResult(false);
            result.setMessage(" 아이디 정보가 정확하지 않습니다. ");
            return result;
        }

        // 사용자 아이디가 존재하는지 체크
        Optional<User> optionalUser = userRepository.findById(parameter.getUserId());

        if(optionalUser.isPresent()){
            result.setResult(false);
            result.setMessage(" 아이디가 이미 존재합니다. ");
            return result;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());



        User user = User.builder()
                .userId(parameter.getUserId())
                .password(encPassword)
                .name(parameter.getName())
                .zipcode(parameter.getZipcode())
                .addr(parameter.getAddr())
                .addrDetail(parameter.getAddrDetail())
                .phone(parameter.getPhone())
                .gender(parameter.getGender())
                .regDt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        result.setResult(true);
        return result;
    }
}
