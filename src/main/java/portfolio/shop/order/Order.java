package portfolio.shop.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.cart.Cart;
import portfolio.shop.delivery.Delivery;
import portfolio.shop.member.Member;
import portfolio.shop.orderItem.OrderItem;
import portfolio.shop.setting.BaseTime;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order extends BaseTime {

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

  /*  public Order(OrderItem orderItem, Member member) {
        changeMember(member);
        settingOrder(orderItem);
        addDeliveryPrice();
    }*/

    public Order(Member member, List<OrderItem> orderItemList
    ) {
        changeMember(member);
        for (OrderItem orderItem : orderItemList) {
            settingOrder(orderItem);
            changeOrderItem(orderItem);
        }
        addDeliveryPrice();
    }

    private void changeMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    private void addOrderItem(OrderItem orderItem){
        orderItem.changeOrder(this);
        orderItems.add(orderItem);
    }

    public void changeOrderItem(OrderItem... orderItems) {
        for (OrderItem orderItem : orderItems) {
            this.addOrderItem(orderItem);
            orderItem.changeOrder(this);
        }
    }

    private void addDeliveryPrice() {
        orderPrice = orderPrice + deliveryPrice;
    }

    /*public void settingOrder(OrderItem orderItem) {
        this.count = this.count + orderItem.getCount();
        this.orderPrice = this.orderPrice + orderItem.getOrderPrice();
        addPrice();
    }*/

    public void settingOrder(OrderItem orderItem) {
        this.count = this.count + orderItem.getCount();
        this.orderPrice = this.orderPrice + orderItem.getOrderPrice();
        addPrice();
    }

    public void addPrice() {
        if (this.orderPrice < 20000) {
            deliveryPrice = 3000;
        }
    }

    public void singleOrderItemPrice(OrderItem orderItem) {

    }


}
