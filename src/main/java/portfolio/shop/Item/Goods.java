package portfolio.shop.Item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.enumType.StockStatus;
import portfolio.shop.exception.OutOfStockException;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Goods {

    @Id @GeneratedValue
    @Column(name = "goods_id")
    private Long id;

    private String size;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    public Goods(String size, int count) {
        this.size = size;
        this.count = count;
    }

    public void changeItem(Item item) {
        this.item = item;
        item.getGoods().add(this);
    }

    public void settingStatus() {
        if (count == 0) {
            stockStatus = StockStatus.SOLDOUT;
        }
        else if (count <= 5) {
            stockStatus = StockStatus.LOW;
        }
        else{
            stockStatus = StockStatus.LOT;
        }
    }

    public void removeStock(int count) {
        int stock = this.count - count;
        if (stock < 0) {
            throw new OutOfStockException("재고 부족");
        }
        this.count = stock;
        settingStatus();
    }

    public void addStock(int count) {
        this.count = this.count + count;
        settingStatus();
    }

}
