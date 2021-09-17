package ko.co.dongyang.study.minilms.user.dto;

import ko.co.dongyang.study.minilms.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private String userId;
    private String password;
    private String name;
    private String zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String gender;
    private LocalDateTime regDt;


    public String getRegDtText() {
        if (regDt != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (HH:mm)");

            return regDt.format(dateTimeFormatter);
        }
        return "";
    }

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

    public static List<UserDto> of(List<User> users) {
        List<UserDto> userList = new ArrayList<>();

        if (users != null) {
            users.forEach(e -> {
                userList.add(UserDto.of(e));
            });
        }

        return userList;
    }
}
