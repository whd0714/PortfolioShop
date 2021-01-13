package portfolio.shop.myPage;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.order.Order;
import portfolio.shop.order.OrderRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class myPageController {

    private final OrderRepository orderRepository;

    //TODO: 주문번호를 어떤식으로 만들지 정해야됨(현재 주문번호는 Order_id), 페이지적용필요, 상세페이지필요?
    @GetMapping("/myPage/orderList")
    public String viewOrderListForm(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        PageRequest request = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));

        //일단 페이지 사용 XXX
        List<Order> content = orderRepository.findByMemberId(member.getId(), request).getContent();

        if (!content.isEmpty()) {
            model.addAttribute("orderList", content);
        }

        return "member/orderList";
    }

    @GetMapping("/myPage/orderList/detail")
    public String viewOrderListDetailForm(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        return "/member/detailOrderList";
    }

}
