package portfolio.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import portfolio.shop.member.dto.MemberSignUpDto;
import portfolio.shop.member.validation.MemberValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberValidator memberValidator;
    private final MemberService memberService;

    @InitBinder("memberSignUpDto")
    public void signUpBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute(new MemberSignUpDto());
        return "member/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid MemberSignUpDto memberSignUpDto, Errors errors) {
        if(errors.hasErrors()) {
            return "member/sign-up";

        }
        memberService.processSignup(memberSignUpDto);

        return "redirect:/";
    }
    
    @GetMapping("/myPage")
    public String myPageForm(@CurrentUser Member member, Model model) {
        if (member != null) {
            model.addAttribute("member",member);
        }

        return "member/myPage";
    }

}
