package portfolio.shop.delivery;

import portfolio.shop.order.Order;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String zipcode;
    private String city;
    private String street;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

}
