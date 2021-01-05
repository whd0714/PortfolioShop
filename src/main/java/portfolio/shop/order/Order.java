package portfolio.shop.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.delivery.Delivery;
import portfolio.shop.member.Member;
import portfolio.shop.orderItem.OrderItem;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private int count;
    private int orderPrice;
    private int deliveryPrice;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public Order(OrderItem orderItem) {
        settingOrder(orderItem);
        addDeliveryPrice();
    }

    private void addDeliveryPrice() {
        orderPrice = orderPrice + deliveryPrice;
    }

    public void settingOrder(OrderItem orderItem) {
        this.count = count + orderItem.getCount();
        this.orderPrice = orderPrice + orderItem.getOrderPrice();
        addPrice();
    }

    public void addPrice() {
        if (this.orderPrice < 20000) {
            deliveryPrice = 3000;
        }
    }

}
