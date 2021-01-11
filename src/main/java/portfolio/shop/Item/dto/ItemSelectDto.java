package portfolio.shop.Item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSelectDto {

    private List<String> name;
    private List<String> size;
    private List<Integer> count;

  /*  private String name;
    private String size;
    private int count;*/
}
