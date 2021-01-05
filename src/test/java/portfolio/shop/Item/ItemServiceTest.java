package portfolio.shop.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import portfolio.enumType.ItemSize;
import portfolio.enumType.ItemType;
import portfolio.shop.Item.dto.ItemFormDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void addItem() {
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setBrandName("nike");
        itemFormDto.setCount(5);
        itemFormDto.setItemImg(null);
        itemFormDto.setItemName("dunk");
        itemFormDto.setItemSize(ItemSize.M);
        itemFormDto.setItemType(ItemType.SHOES);
        itemFormDto.setPrice(100000);

        Long itemId = itemService.addItem(itemFormDto);

        Optional<Item> byId = itemRepository.findById(itemId);

        byId.ifPresent(item -> {
            assertThat(item.getId()).isEqualTo(itemId);
        });
    }

}