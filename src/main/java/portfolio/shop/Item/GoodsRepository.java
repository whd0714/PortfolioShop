package portfolio.shop.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {


    boolean existsBySize(String size);

    @Query("select g from Goods g join fetch g.item i where i.itemName = :itemName and g.size = :size")
    Goods findByItemIdAndSize(@Param("itemName") String itemName, @Param("size") String size);

    @Query("select g from Goods g join g.item i where i.id = :itemId")
    List<Goods> findByItemId(@Param("itemId") Long itemId);

    @Query("select g from Goods g join fetch g.item i where g.id = :goodsId")
    Goods findByGoodsId(@Param("goodsId") Long goodsId);

}
