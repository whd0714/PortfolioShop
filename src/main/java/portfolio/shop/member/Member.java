package portfolio.shop.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor

public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String loginId;
    private String username;
    private String password;

    @Column(unique = true)
    private String email;
    private LocalDateTime joinAt;
    private String uuid;

    public Member(String loginId, String username, String password, String email) {
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void settingJoinTime() {
        this.joinAt = LocalDateTime.now();
    }

}