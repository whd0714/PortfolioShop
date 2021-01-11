package portfolio.shop.Item;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static portfolio.shop.Item.QItem.item;


public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Item> findAllItem() {
        queryFactory
                .select(item)
                .from(item)
                .fetch();
        return null;
    }
}
