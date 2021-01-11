package portfolio.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import portfolio.shop.Item.dto.GoodsAndCount;
import portfolio.shop.Item.dto.GoodsInfoDto;
import portfolio.shop.Item.dto.ItemFormDto;
import portfolio.shop.Item.dto.ItemSelectDto;
import portfolio.shop.cart.CartService;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.order.OrderService;
import portfolio.shop.orderItem.OrderItemRepository;
import portfolio.shop.orderItem.OrderItemService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;
    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final GoodsRepository goodsRepository;
    private final CartService cartService;
    private final OrderItemService orderItemService;
    private final OrderItemRepository orderItemRepository;

    @GetMapping("/goods/add")
    public String addGoodsForm(Model model) {
        model.addAttribute(new ItemFormDto());
        return "item/add";
    }

    @PostMapping("/goods/add")
    public String addGoods(@Valid ItemFormDto itemFormDto, Errors errors) {
        if (errors.hasErrors()) {
            return "item/add";
        }
        goodsService.checkItem(itemFormDto);

        return "redirect:/";
    }

    @GetMapping("/goods/{itmeId}")
    public String viewGoodsForm(@PathVariable("itmeId") Long itemId, Model model, @CurrentUser Member member) {
        //Optional<Goods> findGoods = goodsRepository.findById(goodsID);
        Optional<Item> findItem = itemRepository.findById(itemId);

        if (member != null) {
            model.addAttribute(member);
        }

        findItem.ifPresent(item -> {
            model.addAttribute(item);

            List<Goods> goodsList = goodsRepository.findByItemId(itemId);
            model.addAttribute("goodsList",goodsList);
        });

        //goods가 null일 경우 페이지 확인하기

        model.addAttribute(new ItemSelectDto());
        //model.addAttribute(new CartSendDto());

        //TODO 상품페이지 품절표시 추가, 재고수량 보다 많은 수량을 선택하면 경고창 팝업, 수량 선택 후 구매버튼, 장바구니버튼처리(hidden처리 또는 disabled처리 또는 경고창 팝업)
        return "item/viewForm2";
    }

    //TODO //수정해야됨 => 장바구니담기, 장바구니 구매관련 로직 상품페이지에서 여러 상품을 담는다면 즉시 구매관련 로직 수정필요
    @PostMapping("/order/order_form")
    public String selectGoods(@Valid ItemSelectDto itemSelectDto, Errors errors,
                                    @CurrentUser Member member, Model model,  HttpServletRequest request) {

        if (member != null) {
            model.addAttribute(member);
        }

        //수정해야됨
        Goods goods = goodsRepository.findByItemIdAndSize(itemSelectDto.getName().get(0), itemSelectDto.getSize().get(0));

        if(member == null){
            //TODO 로그인 하지 않은 상태로 물건 구매하면 경고창 팝업
        }
        goodsService.addProductToCart(goods, member.getId(), itemSelectDto);

        request.setAttribute("goods",goods);

        //수정해야됨
        request.setAttribute("count",itemSelectDto.getCount().get(0));
        return "forward:/order/goods";
    }

    //TODO 즉시구매와 장바구니 구매를 하나의 핸들러로 처리(지금은 각각 다른 핸들러로 처리됨)
    @PostMapping("/order/cart_order_form")
    public String cartOrder(@CurrentUser Member member, Model model, ItemSelectDto itemSelectDto) {
        if (member != null) {
            model.addAttribute(member);
        }

        List<Goods> goodsList = new ArrayList<>();

        List<GoodsAndCount> goodsAndCounts = new ArrayList<>();

        if (!itemSelectDto.getCount().isEmpty()) {
            for (int i = 0; i < itemSelectDto.getCount().size(); i++) {
                Goods goods = goodsRepository.findByItemIdAndSize(itemSelectDto.getName().get(i), itemSelectDto.getSize().get(i));
                GoodsAndCount goodsAndCount = new GoodsAndCount(goods, itemSelectDto.getCount().get(i));
                goodsAndCounts.add(goodsAndCount);

            }
        }

        model.addAttribute("goodsAndCountLIst", goodsAndCounts);
        model.addAttribute(new GoodsInfoDto());

        return "order/order_form";
    }

    @PostMapping("/order/goods")
    public String orderFormView(@CurrentUser Member member, Model model, HttpServletRequest request) {
        if (member != null) {
            model.addAttribute(member);
        }

        Goods goods = (Goods)request.getAttribute("goods");
        int count = (int) request.getAttribute("count");

        List<GoodsAndCount> goodsAndCounts = new ArrayList<>();
        GoodsAndCount goodsAndCount = new GoodsAndCount(goods, count);
        goodsAndCounts.add(goodsAndCount);
        model.addAttribute("goodsAndCountLIst", goodsAndCounts);

        model.addAttribute(new GoodsInfoDto());

        return "order/order_form";
    }

}
