package portfolio.shop.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join o.member m where m.id = :memberId")
    List<Order> findByMemberId(@Param("memberId") Long memberId);
}
