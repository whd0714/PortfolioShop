package portfolio.shop.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.shop.Item.Goods;
import portfolio.shop.Item.GoodsRepository;
import portfolio.shop.Item.Item;
import portfolio.shop.Item.ItemRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;
import portfolio.shop.order.Order;
import portfolio.shop.order.OrderRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GoodsRepository goodsRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/")
    public String main(@CurrentUser Member member, Model model){
        if(member != null){
            model.addAttribute(member);
        }

        List<Order> orderList = orderRepository.findByMemberId(member.getId());
        if (!orderList.isEmpty()) {
            model.addAttribute("orderList",orderList);
        }

        List<Item> allItem = itemRepository.findAll();
        if (!allItem.isEmpty()) {
            model.addAttribute("allItem",allItem);
        }
        /*List<Goods> goods = goodsRepository.findAll();
        if (goods != null) {
            model.addAttribute(goods);
        }*/
            return "index";
    }


    @GetMapping("/login")
    public String login(){
        return "member/login";
    }


}
