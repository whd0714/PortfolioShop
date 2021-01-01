package portfolio.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.member.dto.MemberSignUpDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void processSignup(MemberSignUpDto memberSignUpDto) {
        String password = passwordEncoder.encode(memberSignUpDto.getPassword());
        Member member = Member.builder()
                .loginId(memberSignUpDto.getLoginId())
                .username(memberSignUpDto.getUsername())
                .password(password)
                .email(memberSignUpDto.getEmail())
                .build();
        member.settingJoinTime();

        memberRepository.save(member);

        login(member);
    }

    public void login(Member member) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                member.getLoginId(),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
