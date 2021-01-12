package portfolio.shop.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.shop.cart.CartGoodsRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.order.Order;
import portfolio.shop.order.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final OrderRepository orderRepository;
    private final CartGoodsRepository cartGoodsRepository;

    @GetMapping("/myPage/notification")
    public String myPageFormView(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        List<Order> orderList = new ArrayList<>();
        Pageable request = PageRequest.of(0, 10);

        if (member != null) {
            List<Order> order = orderRepository.findByMemberId(member.getId(), request).getContent();
            if (member.getCart() != null) {
                Long cartId = member.getCart().getId();
            }
            orderList = order;
        }

        if (!orderList.isEmpty()) {
            model.addAttribute("orderList",orderList);
        }

        return "member/notification";
    }
}
