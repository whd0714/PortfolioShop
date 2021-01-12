package portfolio.shop.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;

@Controller
@RequiredArgsConstructor
public class TagController {

    @GetMapping("/myPage/keyword")
    public String keywordFormView(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute(member);
        }

        return "member/keyword";
    }
}
