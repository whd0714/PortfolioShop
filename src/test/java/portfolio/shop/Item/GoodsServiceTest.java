package portfolio.shop.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import portfolio.enumType.ItemType;
import portfolio.shop.Item.dto.ItemFormDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void addItem() {

        ItemFormDto itemFormDto = new ItemFormDto("NIKE", "나이키티", 50000, null,
                "m", 50, "레이어드 크루 넥 반팔",ItemType.TOP);

        goodsService.addItem(itemFormDto);

        List<Goods> all = goodsRepository.findAll();

        for (Goods goods : all) {
            System.out.println("goods = " + goods);
        }
    }

}