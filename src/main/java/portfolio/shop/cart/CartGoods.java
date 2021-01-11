package portfolio.shop.cart;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.Goods;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartGoods {

    @Id @GeneratedValue
    @Column(name = "cart_goods_id")
    private Long id;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public CartGoods(Goods goods) {
        this.goods = goods;
    }

    public void changeCart(Cart cart) {
        this.cart = cart;
        cart.getCartGoodsList().add(this);
    }

    public void changeCount(int count) {
        this.count = count;
    }

}
