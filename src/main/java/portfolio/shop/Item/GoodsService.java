package portfolio.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.Item.dto.ItemFormDto;
import portfolio.shop.Item.dto.ItemSelectDto;
import portfolio.shop.cart.Cart;
import portfolio.shop.cart.CartGoods;
import portfolio.shop.cart.CartGoodsRepository;
import portfolio.shop.cart.CartRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;
    private final ItemRepository itemRepository;
    private final CartGoodsRepository cartGoodsRepository;
    private final CartRepository cartRepository;

/*    public void addItem(ItemFormDto itemFormDto) {

        Goods goods = new Goods(itemFormDto.getSize(), itemFormDto.getCount());

        Item item = new Item(itemFormDto.getBrandName(), itemFormDto.getItemName(), itemFormDto.getItemImg(),
                itemFormDto.getItemType(), itemFormDto.getPrice(), itemFormDto.getTitle());

        goods.changeItem(item);
        goods.settingStatus();

        itemRepository.save(item);

        goodsRepository.save(goods);
    }*/

    public void addItem(ItemFormDto itemFormDto) {

        Goods goods = new Goods(itemFormDto.getSize(), itemFormDto.getCount());

        Item item = new Item(itemFormDto.getBrandName(), itemFormDto.getItemName(), itemFormDto.getItemImg(),
                itemFormDto.getItemType(), itemFormDto.getPrice(), itemFormDto.getTitle());

        goods.changeItem(item);
        goods.settingStatus();

        itemRepository.save(item);

        goodsRepository.save(goods);
    }

    public void checkItem(ItemFormDto itemFormDto) {
        Goods goods = goodsRepository.findByItemIdAndSize(itemFormDto.getItemName(), itemFormDto.getSize());

        if (!itemRepository.existsByItemName(itemFormDto.getItemName())) {
            addItem(itemFormDto);
        } else if (goodsRepository.findByItemIdAndSize(itemFormDto.getItemName(), itemFormDto.getSize()) == null) {
            updateItem(itemFormDto);
        } else {
            plusStock(itemFormDto);
        }
        /*if (itemRepository.existsByItemName(itemFormDto.getItemName()) && goodsRepository.existsBySize(itemFormDto.getSize())) {
            plusStock(itemFormDto);
        }
        else if (itemRepository.existsByItemName(itemFormDto.getItemName())) {
            updateItem(itemFormDto);
        }else{
            addItem(itemFormDto);
        }*/
    }

    private void plusStock(ItemFormDto itemFormDto) {
        Item item = itemRepository.findByItemName(itemFormDto.getItemName());
        List<Long> collect = item.getGoods().stream().map(Goods::getId).collect(Collectors.toList());
        for (Long aLong : collect) {
            Optional<Goods> byId = goodsRepository.findById(aLong);
            byId.ifPresent(goods -> {
                goods.addStock(itemFormDto.getCount());
                goods.settingStatus();
            });
        }
    }

    private void updateItem(ItemFormDto itemFormDto) {
        Item findItem = itemRepository.findByItemName(itemFormDto.getItemName());
        Goods goods = new Goods(itemFormDto.getSize(), itemFormDto.getCount());
        goods.changeItem(findItem);
        goods.settingStatus();

        goodsRepository.save(goods);
    }

    public void addProductToCart(Goods goods, Long memberId, ItemSelectDto itemSelectDto) {
        Cart cart = cartRepository.findByMember(memberId);

        CartGoods findCartGoods = cartGoodsRepository.findByGoodsAndMember(goods.getId(), memberId);
        if(findCartGoods == null){
            CartGoods cartGoods = new CartGoods(goods);
            cartGoods.changeCart(cart);
            //수정해야됨
            cartGoods.changeCount(itemSelectDto.getCount().get(0));

            cartGoodsRepository.save(cartGoods);
        }
    }
}
