package ko.co.dongyang.study.minilms.admin.mapper;

import ko.co.dongyang.study.minilms.user.dto.UserDto;
import ko.co.dongyang.study.minilms.user.model.UserSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int selectListCount(UserSearch parameter);

    List<UserDto> selectList(UserSearch parameter);
}
