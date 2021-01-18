package portfolio.shop.wish;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.Item;
import portfolio.shop.member.Member;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {

    @Id @GeneratedValue
    @Column(name = "wish_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Wish(Member member, Item item) {
        this.member = member;
        changeWish(member);

        this.item = item;
    }

    private void changeWish(Member member) {
        this.member = member;
        member.getWishes().add(this);
    }


}
