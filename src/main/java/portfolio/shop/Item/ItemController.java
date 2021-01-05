package portfolio.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import portfolio.shop.Item.dto.ItemFormDto;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

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

        itemService.addItem(itemFormDto);
        return "redirect:/";
    }


}
