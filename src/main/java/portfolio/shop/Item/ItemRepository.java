package portfolio.shop.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

    Item findByItemName(String itemName);

    boolean existsByItemName(String itemName);
}
