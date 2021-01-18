package portfolio.shop.Item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.enumType.ItemType;
import portfolio.shop.tagItem.TagItem;
import portfolio.shop.wish.Wish;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String brandName;
    private String itemName;
    private String itemImg;
    private int price;
    private String title;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @OneToMany(mappedBy = "item")
    private List<Goods> goods = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Wish> wishes = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<TagItem> tagItems = new ArrayList<>();

    public Item(String brandName, String itemName, String itemImg, ItemType itemType, int price, String title) {
        this.brandName = brandName;
        this.itemName = itemName;
        this.itemImg = itemImg;
        this.itemType = itemType;
        this.price = price;
        this.title = title;
    }



}
