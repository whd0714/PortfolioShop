package portfolio.shop.wish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishControllerTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testAddWish(){
        Member member = new Member("aaaaa", "회원A", "123456789", "aaa@naver.com");


    }


}