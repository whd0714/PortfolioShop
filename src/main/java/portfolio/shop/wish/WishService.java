package portfolio.shop.wish;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.Item.Item;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {

    private final MemberRepository memberRepository;
    private final WishRepository wishRepository;

    public void addWishProcess(Long id, Item item) {

        Optional<Member> member = memberRepository.findById(id);
        member.ifPresent(m -> {
            Wish wish = wishRepository.findByMemberAndItem(m.getId(), item.getId());
            if (wish == null) {
                Wish w = new Wish(m, item);
                wishRepository.save(w);
            }else {
                wishRepository.delete(wish);
            }
        });

    }

    public void getWishItemList(Long memberId) {
        //wishRepository.findByMember();
    }
}
