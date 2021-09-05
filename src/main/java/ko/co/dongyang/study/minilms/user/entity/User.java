package ko.co.dongyang.study.minilms.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ZIPCODE")
    private String zipcode;
    @Column(name = "ADDR")
    private String addr;
    @Column(name = "ADDR_DETAIL")
    private String addrDetail;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "REG_DT")
    private LocalDateTime regDt;

}
