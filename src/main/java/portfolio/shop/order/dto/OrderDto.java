package portfolio.shop.order.dto;

import lombok.Data;
import portfolio.shop.delivery.Delivery;
import portfolio.shop.member.Member;

@Data
public class OrderDto {

    private Delivery delivery;
    private Member member;
    private int count;
    private int orderPrice;
    private int deliveryPrice;


}
