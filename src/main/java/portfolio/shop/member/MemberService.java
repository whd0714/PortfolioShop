package portfolio.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.member.dto.MemberSignUpDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

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
        member.settingSignUp();

        memberRepository.save(member);

        login(member);
    }

    public void login(Member member) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserMember(member),
                member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId);
        if (member == null) {
            throw new UsernameNotFoundException(loginId);
        }

        return new UserMember(member);
    }
}
