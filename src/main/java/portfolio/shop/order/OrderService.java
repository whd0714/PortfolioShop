package portfolio.shop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import portfolio.shop.Item.Goods;
import portfolio.shop.Item.GoodsRepository;
import portfolio.shop.Item.ItemRepository;
import portfolio.shop.Item.dto.GoodsInfoDto;
import portfolio.shop.cart.Cart;
import portfolio.shop.cart.CartGoods;
import portfolio.shop.cart.CartGoodsRepository;
import portfolio.shop.cart.CartRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;
import portfolio.shop.orderItem.OrderItem;
import portfolio.shop.orderItem.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
/*
    public void order(Long orderItemId, Member member) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
        orderItem.ifPresent(oi -> {
            Order order = new Order(oi, member);

            orderRepository.save(order);
            
            order.changeOrderItem(oi);

            Goods goods = oi.getGoods();
            goods.removeStock(oi.getCount());
        });

    }*/

}
