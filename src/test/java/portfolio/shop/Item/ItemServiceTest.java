package portfolio.shop.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import portfolio.enumType.ItemType;
import portfolio.shop.Item.dto.ItemFormDto;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

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


       // Long itemId = itemService.addItem(itemFormDto);

        //Optional<Item> findByItem = itemRepository.findById(itemId);

       // findByItem.ifPresent(item -> assertThat(item.getItemName()).isEqualTo("나이키티"));

    }

}