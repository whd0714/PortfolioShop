package portfolio.shop.Item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsInfoDto {

    private List<Long> id;
    private List<Integer> count;

}
