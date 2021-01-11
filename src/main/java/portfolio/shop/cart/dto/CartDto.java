package portfolio.shop.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String itemName;
    private int itemPrice;
    private int count;
    private int orderPrice;

}
