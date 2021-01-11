package portfolio.shop.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByLoginId(String loginId);

    boolean existsByEmail(String loginId);

    Member findByLoginId(String member1);

    @Query("select m from Member m join m.orders where m.id = :memberId")
    Member findByJoinOrder(@Param("memberId") Long membmerId);

}
