package portfolio.shop.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("select w from Wish w join fetch w.item i join fetch w.member m where m.id =:memberId and i.id = :itemId")
    Wish findByMemberAndItem(@Param("memberId") Long memberId, @Param("itemId") Long itemId);

    @Query("select count (w) from Wish w join w.item i where i.id = :itemId")
    int getCountWithItem(@Param("itemId") Long itemId);



   // Wish findByMember();
}
