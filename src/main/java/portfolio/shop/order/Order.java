package portfolio.shop.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    private int count;
    private int orderPrice;
    private int deliveryPrice;

    public void addPrice() {
        if (this.orderPrice < 20000) {
            deliveryPrice = 3000;
        }
    }

}
