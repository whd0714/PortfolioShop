package portfolio.shop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import portfolio.shop.Item.Goods;
import portfolio.shop.Item.GoodsRepository;
import portfolio.shop.Item.GoodsService;
import portfolio.shop.Item.dto.ItemSelectDto;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.orderItem.OrderItemRepository;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;
    private final GoodsService goodsService;

    @GetMapping("/cart")
    private String cartForm(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        // TODO 화면에 데이터 dto로 변환 전 후 쿼리 체크

        //page 안들어감 page 추가해야됨
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "username"));

        Cart cart = cartRepository.findByMember(member.getId());

        if (cart != null) {
            model.addAttribute(cart);
            model.addAttribute(new ItemSelectDto());
           // model.addAllAttributes()
        }

        return "order/cart";
    }

    @PostMapping("/cart/add")
    public String addGoods(@CurrentUser Member member, ItemSelectDto itemSelectDto, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }
        //상품페이지에서 하나의 상품을 장바구니에 담도록 해놨음,
        // 하나의 상품페이지에서 여러개의 아이템을 담도록 구현할때는 여러개의 상품이 장바구니에 들어가도록 수정해야됨
        Goods goods = goodsRepository.findByItemIdAndSize(itemSelectDto.getName().get(0), itemSelectDto.getSize().get(0));

        if(member == null){
            //TODO 로그인 하지 않은 상태로 물건 구매하면 경고창 팝업
        }
        goodsService.addProductToCart(goods, member.getId(), itemSelectDto);

        return "redirect:/cart";
    }

}
