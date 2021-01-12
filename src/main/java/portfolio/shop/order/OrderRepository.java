package portfolio.shop.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o join o.member m where m.id = :memberId order by o.id desc")
    Page<Order> findByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
