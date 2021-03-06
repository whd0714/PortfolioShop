package portfolio.shop.main;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.shop.Item.GoodsRepository;
import portfolio.shop.Item.Item;
import portfolio.shop.Item.ItemRepository;
import portfolio.shop.cart.CartGoods;
import portfolio.shop.cart.CartGoodsRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;
import portfolio.shop.order.Order;
import portfolio.shop.order.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GoodsRepository goodsRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final CartGoodsRepository cartGoodsRepository;

    @GetMapping("/")
    public String main(@CurrentUser Member member, Model model){
        if(member != null){
            model.addAttribute(member);
        }

        List<Order> orderList = new ArrayList<>();
        Pageable request = PageRequest.of(0, 10);

        if (member != null) {
            List<Order> order = orderRepository.findByMemberId(member.getId(), request).getContent();
            if (member.getCart() != null) {
                Long cartId = member.getCart().getId();
                int totalCount = cartGoodsRepository.findByCartId(cartId, request).getNumberOfElements();
                model.addAttribute("totalCount",totalCount);
            }
            orderList = order;
        }

        if (!orderList.isEmpty()) {
            model.addAttribute("orderList",orderList);
        }

        List<Item> allItem = itemRepository.findAll();
        if (!allItem.isEmpty()) {
            model.addAttribute("allItem",allItem);
        }
            return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }


}
