package portfolio.shop.orderItem;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.Goods;
import portfolio.shop.Item.Item;
import portfolio.shop.cart.Cart;
import portfolio.shop.order.Order;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Enumerated(EnumType.STRING)
    private OrderItemType orderItemType;

    public OrderItem(Goods goods) {
        this.goods = goods;
    }

    public void settingPrice(int count, int price) {
        this.count = count;
        this.orderPrice = price * count;
        //int price = goods.getItem().getPrice();

        // this.orderPrice = price * count;
    }

    public void changeOrder(Order order) {
        this.order = order;
    }



}
