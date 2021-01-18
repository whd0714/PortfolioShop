package portfolio.shop.wish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolio.shop.Item.Item;
import portfolio.shop.Item.ItemRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;
import portfolio.shop.wish.dto.AddWishDto;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;
    private final ItemRepository itemRepository;
    private final WishRepository wishRepository;
    private final MemberRepository memberRepository;

    @PostMapping("/goods/addWish")
    @ResponseBody
    public int addWish(@CurrentUser Member member, @RequestBody AddWishDto addWishDto) {

        Item item = itemRepository.findByItemName(addWishDto.getName());
        wishService.addWishProcess(member.getId(), item);
        int totalCount = wishRepository.getCountWithItem(item.getId());

        /*Optional<Item> item = itemRepository.findById(addWishDto.getItemId());
        item.ifPresent(i->{
            wishService.addWishProcess(member.getId(), i);
        });

        int count = wishRepository.getCountWithItem(addWishDto.getItemId());

        return count;*/

        return totalCount;
    }

    @GetMapping("/myPage/wish")
    public String wishFormView(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        wishService.getWishItemList(member.getId());


        return "member/wish";
    }

}
