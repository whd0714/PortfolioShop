package portfolio.shop.Item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.ItemSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemOrderDto {

    private int count;
    private String itemSize;

}
