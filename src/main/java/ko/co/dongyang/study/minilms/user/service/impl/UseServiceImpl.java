package ko.co.dongyang.study.minilms.user.service.impl;

import ko.co.dongyang.study.minilms.admin.mapper.UserMapper;
import ko.co.dongyang.study.minilms.user.dto.UserDto;
import ko.co.dongyang.study.minilms.user.entity.User;
import ko.co.dongyang.study.minilms.user.model.ServiceResult;
import ko.co.dongyang.study.minilms.user.model.UserRegister;
import ko.co.dongyang.study.minilms.user.model.UserSearch;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UseServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ServiceResult addUser(UserRegister parameter) {

        if (parameter.getUserId() == null || (parameter.getUserId().length()) < 1) {

            return ServiceResult.fail(" 아이디 정보가 정확하지 않습니다. ");
        }

        // 사용자 아이디가 존재하는지 체크
        Optional<User> optionalUser = userRepository.findById(parameter.getUserId());

        if (optionalUser.isPresent()) {

            return ServiceResult.fail(" 아이디가 이미 존재합니다. ");
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

        return ServiceResult.success();
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

    @Override
    public ServiceResult<UserDto> getUsers(UserSearch parameter) {

        return ServiceResult.success(userMapper.selectListCount(parameter), userMapper.selectList(parameter));
    }

    @Override
    public ServiceResult<UserDto> getDetail(UserSearch parameter) {

        Optional<User> optionalUser = userRepository.findById(parameter.getUserId());
        if(!optionalUser.isPresent()){
            return ServiceResult.fail(" 회원 정보가 존재하지 않습니다. ");
        }

        User user = optionalUser.get();

        return ServiceResult.success(UserDto.of(user));
    }

    @Override
    public ServiceResult<UserDto> delete(String userId) {

        userRepository.deleteById(userId);

        return ServiceResult.success();
    }

    @Override
    public ServiceResult<UserDto> reqResetPassword(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        // 리셋 정보 저장
        String pwdResetKey = UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
//        user.setPwdRestKey(pwdResetKey);
//        user.setPwdResetPeriodDt(LocalDateTime.now().plusDays(1));

        return null;
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
