package portfolio.shop.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import portfolio.enumType.ItemType;
import portfolio.shop.Item.GoodsService;
import portfolio.shop.Item.dto.ItemFormDto;
import portfolio.shop.Item.dto.ItemSelectDto;
import portfolio.shop.member.Member;
import portfolio.shop.member.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void order() {
        ItemFormDto itemFormDto = new ItemFormDto("NIKE", "나이키티", 50000, null,
                "m", 50, "레이어드 크루 넥 반팔",ItemType.TOP);

        goodsService.addItem(itemFormDto);

        //ItemSelectDto selectItem = new ItemSelectDto("m", 5);

        Member member = new Member("member1", "userA", "123456789", "email@naver.com");
        memberRepository.save(member);

        //orderService.buyGoods(1l, selectItem, member);
    }
}