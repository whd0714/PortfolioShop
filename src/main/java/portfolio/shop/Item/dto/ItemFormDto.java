package portfolio.shop.Item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.ItemSize;
import portfolio.enumType.ItemType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ItemFormDto {

    private String brandName;
    private String itemName;
    private int price;
    private String itemImg;
    private String size;
    private int count;
    private String title;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    public ItemFormDto(String brandName, String itemName, int price, String itemImg, String size, int count, String title, ItemType itemType) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.price = price;
        this.itemImg = itemImg;
        this.size = size;
        this.count = count;
        this.title = title;
        this.itemType = itemType;
    }
}
