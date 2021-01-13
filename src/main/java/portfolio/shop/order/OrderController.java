package portfolio.shop.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import portfolio.shop.Item.Goods;
import portfolio.shop.Item.GoodsRepository;
import portfolio.shop.Item.dto.GoodsInfoDto;
import portfolio.shop.Item.dto.ItemSelectDto;
import portfolio.shop.cart.Cart;
import portfolio.shop.cart.CartGoods;
import portfolio.shop.cart.CartGoodsRepository;
import portfolio.shop.cart.CartRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.orderItem.OrderItem;
import portfolio.shop.orderItem.OrderItemRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final CartGoodsRepository cartGoodsRepository;
    private final CartRepository cartRepository;



    @PostMapping("/order/createOrderItem")
    public String createOrderItem(@CurrentUser Member member, GoodsInfoDto goodsInfoDto, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        List<Long> goodsId = goodsInfoDto.getId();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (int i = 0; i < goodsId.size(); i++) {

            Goods goods = goodsRepository.findByGoodsId(goodsId.get(i));

            //수정해야됨
            goods.removeStock(goodsInfoDto.getCount().get(i));

            OrderItem orderItem = new OrderItem(goods);

            //수정해야됨
            orderItem.settingPrice(goodsInfoDto.getCount().get(i), goods.getItem().getPrice());
            orderItemRepository.save(orderItem);

            orderItemList.add(orderItem);

            Cart cart = cartRepository.findByMember(member.getId());

            List<CartGoods> cartGoodsList = cart.getCartGoodsList();

            for (CartGoods cartGoods : cartGoodsList) {
                if (cartGoods.getGoods().getId() == goods.getId()) {
                    cart.removeCartGoods(cartGoods);
                    cartGoodsRepository.delete(cartGoods);
                }
            }

        }

        Order order = new Order(member, orderItemList);
        orderRepository.save(order);

        return "order/complete_form";
    }
}
