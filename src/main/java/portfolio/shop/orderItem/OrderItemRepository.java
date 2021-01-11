package portfolio.shop.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select oi from OrderItem oi join oi.goods g where g.id = :id")
    OrderItem findBySameGoods(@Param("id") Long id);

    //@Query("select oi from OrderItem oi join oi.cart c join fetch c.member m where m.id = :id")
    //List<OrderItem> findByMemberAndCart(@Param("id") Long id);
}
