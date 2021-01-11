package portfolio.shop.delivery;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String zipcode;
    private String city;
    private String street;

}
