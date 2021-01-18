package portfolio.shop.tagItem;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.Item;
import portfolio.shop.tag.Tag;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public TagItem(Item item) {
        changeItem(item);

    }

    public void changeItem(Item item) {
        this.item = item;
        item.getTagItems().add(this);
    }
}
