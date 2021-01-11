package portfolio.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import portfolio.shop.Item.dto.ItemFormDto;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.order.OrderService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping("/item/add")
    public String addItemForm(Model model){
        model.addAttribute("itemFormDto",new ItemFormDto());
        return "item/add";
    }

    @PostMapping("/item/add")
    public String addItem(@Valid ItemFormDto itemFormDto, Errors errors) {
        if (errors.hasErrors()) {
            return "item/add";
        }
        //itemService.addItem(itemFormDto);
        return "redirect:/";
    }

    @GetMapping("/item/{itemId}")
    public String itemViewForm(@PathVariable("itemId") Item item, Model model) {
        Optional<Item> findItem = itemRepository.findById(item.getId());

        findItem.ifPresent(i -> model.addAttribute("item", i));
        if (findItem == null) {
            new IllegalStateException("itemId");
        }

        return "item/viewItem";
    }

    @PostMapping("/item/{itemId}")
    public String buyItem(@PathVariable("itemId") Item item, @RequestParam("size") String size,
                          @RequestParam("count") int count, @CurrentUser Member member) {



        return "redirect:/order/order_form";
    }


}
