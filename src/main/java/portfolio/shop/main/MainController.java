package portfolio.shop.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String main(@CurrentUser Member member, Model model){
        if(member != null){
            model.addAttribute("member",member);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

}
