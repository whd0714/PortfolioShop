package portfolio.shop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartGoodsRepository extends JpaRepository<CartGoods, Long> {

    @Query("select cg from CartGoods cg join cg.goods g join fetch cg.cart c where g.id = :id and c.member.id = :memberId")
    CartGoods findByGoodsAndMember(@Param("id") Long goodsId, @Param("memberId")Long MemberId);
}
