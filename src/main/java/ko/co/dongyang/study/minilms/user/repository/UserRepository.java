package ko.co.dongyang.study.minilms.user.repository;

import ko.co.dongyang.study.minilms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
