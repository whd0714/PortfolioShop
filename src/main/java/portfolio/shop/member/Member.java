package portfolio.shop.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import portfolio.shop.cart.Cart;
import portfolio.shop.delivery.Address;
import portfolio.shop.order.Order;
import portfolio.shop.setting.BaseTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "loginId", "userName", "password", "email", "joinAt", "profileImg"})
public class Member extends BaseTime {

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

    private Address address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member")
    private Cart cart;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(String loginId, String userName, String password, String email) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public void settingSignUp() {
        this.joinAt = LocalDateTime.now();
    }

    public void changeCart(Cart cart) {
        this.cart = cart;
    }
}
