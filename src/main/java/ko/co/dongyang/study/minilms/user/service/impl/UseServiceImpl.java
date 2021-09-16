package ko.co.dongyang.study.minilms.user.service.impl;

import ko.co.dongyang.study.minilms.user.dto.UserDto;
import ko.co.dongyang.study.minilms.user.entity.User;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import ko.co.dongyang.study.minilms.user.repository.UserRepository;
import ko.co.dongyang.study.minilms.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UseServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ServiceResult addUser(UserRegister parameter) {

        ServiceResult result = new ServiceResult();

        if (parameter.getUserId() == null || (parameter.getUserId().length()) < 1) {
            result.setResult(false);
            result.setMessage(" 아이디 정보가 정확하지 않습니다. ");
            return result;
        }

        // 사용자 아이디가 존재하는지 체크
        Optional<User> optionalUser = userRepository.findById(parameter.getUserId());

        if (optionalUser.isPresent()) {
            result.setResult(false);
            result.setMessage(" 아이디가 이미 존재합니다. ");
            return result;
        }

        // 비밀번호 암호화
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

    @Override
    public UserDto getUser(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            return null;
        }
        User user = optionalUser.get();

        return UserDto.of(user);

    }

    // SecurityConfiguration 관련 설정
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findById(username);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (user.isAdminYn()) {
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }


        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), grantedAuthorityList);
    }
}
