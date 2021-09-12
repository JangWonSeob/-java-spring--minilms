package ko.co.dongyang.study.minilms.user.dto;

import ko.co.dongyang.study.minilms.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserDto {

    private String userId;
    private String password;
    private String name;
    private String zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String gender;
    private LocalDateTime regDt;

    public static UserDto of(User user) {

        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .zipcode(user.getZipcode())
                .addr(user.getAddr())
                .addrDetail(user.getAddrDetail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .regDt(user.getRegDt())
                .build();

        return userDto;
    }
}
