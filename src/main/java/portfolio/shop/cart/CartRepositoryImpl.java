package portfolio.shop.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import portfolio.shop.member.QMember;

import javax.persistence.EntityManager;

import static portfolio.shop.cart.QCart.cart;
import static portfolio.shop.member.QMember.member;

public class CartRepositoryImpl implements CartRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CartRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Cart findByOrderItemAndGoods(Long memberId) {
        queryFactory
                .select(cart)
                .from(cart)
                .join(cart.member, member).fetchJoin()
                .where(member.id.eq(memberId))
                .fetch();

        return null;
    }
}
