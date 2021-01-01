package portfolio.shop.member.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;
import portfolio.shop.member.dto.MemberSignUpDto;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return MemberSignUpDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MemberSignUpDto member = (MemberSignUpDto) o;

        if(memberRepository.existsByLoginId(member.getLoginId())) {
            errors.rejectValue("loginId","error.loginId",null,"이미 사용하고 있는 아이디 입니다.");
        }
        if(memberRepository.existsByEmail(member.getLoginId())) {
            errors.rejectValue("email","error.email",null,"이미 사용하고 있는 이메일 입니다.");
        }
        if(!member.getPassword().equals(member.getPasswordConfirm())) {
            errors.rejectValue("password","error.password",null,"패스워드가 일치 하지 않습니다.");
        }
    }
}
