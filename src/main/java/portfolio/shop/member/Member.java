package portfolio.shop.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "loginId", "userName", "password", "email", "joinAt", "profileImg"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String loginId;
    private String userName;
    private String password;

    @Column(unique = true)
    private String email;
    private LocalDateTime joinAt;
    private String profileImg;

    public Member(String loginId, String userName, String password, String email) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public void settingSignUp() {
        this.joinAt = LocalDateTime.now();
    }
}
