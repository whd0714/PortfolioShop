package portfolio.shop.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.member.dto.MemberSignUpDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void createMember() {
        MemberSignUpDto memberSignUpDto = new MemberSignUpDto();
        memberSignUpDto.setEmail("member1@naver.com");
        memberSignUpDto.setLoginId("member1");
        memberSignUpDto.setPassword("123456789");
        memberSignUpDto.setPasswordConfirm("123456789");
        memberSignUpDto.setUsername("user");

        memberService.processSignup(memberSignUpDto);

        Member findMember = memberRepository.findByLoginId("member1");

        assertThat(findMember.getLoginId()).isEqualTo("member1");
    }


}