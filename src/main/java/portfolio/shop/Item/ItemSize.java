package portfolio.shop.Item;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemSize {

    private int s;
    private int m;
    private int l;
    private int xl;

}
