package portfolio.shop.tagItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagItemRepository extends JpaRepository<TagItem, Long> {

    @Query("select ti from TagItem ti join fetch ti.item i where i.id = :itemId")
    TagItem findByItem(@Param("itemId") Long itemId);
}
