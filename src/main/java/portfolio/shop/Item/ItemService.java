package portfolio.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.Item.dto.ItemFormDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;



 /*   public Long addItem(ItemFormDto itemFormDto) {
        Item item = new Item(itemFormDto.getBrandName(), itemFormDto.getItemName(), itemFormDto.getCount(), itemFormDto.getPrice(),
                itemFormDto.getItemImg() ,itemFormDto.getItemSize(), itemFormDto.getItemType());

        Item save = itemRepository.save(item);

        return save.getId();
    }*/

}
