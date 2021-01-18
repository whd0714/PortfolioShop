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
import portfolio.shop.cart.Cart;
import portfolio.shop.cart.CartRepository;
import portfolio.shop.member.dto.MemberSignUpDto;
import portfolio.shop.wish.Wish;
import portfolio.shop.wish.WishRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final WishRepository wishRepository;

    public void processSignup(MemberSignUpDto memberSignUpDto) {
        String password = passwordEncoder.encode(memberSignUpDto.getPassword());
        Member member = new Member(memberSignUpDto.getLoginId(), memberSignUpDto.getUsername(), password, memberSignUpDto.getEmail());
        member.settingSignUp();

        memberRepository.save(member);

        createCart(member);
        login(member);
    }

    public void createCart(Member member) {
        Cart cart = new Cart(member);
        cartRepository.save(cart);
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
