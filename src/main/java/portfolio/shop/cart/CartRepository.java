package portfolio.shop.cart;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "select c from Cart c join fetch c.member m where m.id = :id")
    Cart findByMember(@Param("id") Long id);

    @Query("select c from Cart c where exists (select oi from OrderItem oi where oi.id = :id)")
    boolean existsByMemberId(@Param("id") Long id);

/*    boolean existsByMemberId(Long id);

    boolean existsByOrderItemId(Long id);*/
}
