package portfolio.shop.cart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.Goods;
import portfolio.shop.member.Member;
import portfolio.shop.order.Order;
import portfolio.shop.orderItem.OrderItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    //TODO 만약에 좋아요 기능을 추가 하면 cart와 좋아요 엔티티를 상속하는 추상 클래스 생성 할 예정

    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Cart(Member member) {
        this.member = member;
    }
}
