package portfolio.shop.Item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.shop.Item.Goods;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsAndCount {

    private Goods goods;
    private int count;

    /*private List<Goods> goods = new ArrayList<>();
    private List<Integer> count = new ArrayList<>();*/

}
