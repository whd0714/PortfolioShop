package portfolio.shop.Item.dto;

import lombok.Data;
import portfolio.enumType.ItemSize;
import portfolio.enumType.ItemType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ItemFormDto {

    private String brandName;
    private String itemName;
    private int count;
    private int price;
    private String itemImg;

    @Enumerated(EnumType.STRING)
    private ItemSize itemSize;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

}
