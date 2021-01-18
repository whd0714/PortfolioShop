package portfolio.shop.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import portfolio.shop.Item.Item;
import portfolio.shop.member.Member;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select t from Tag t where t.tagType = :tagType")
    List<Tag> findByTagType(@Param("tagType") TagType tagType);

    Tag findByTagName(String tagName);


}
