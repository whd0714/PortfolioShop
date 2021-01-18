package portfolio.shop.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.Item.Item;
import portfolio.shop.member.Member;
import portfolio.shop.tagItem.TagItem;
import portfolio.shop.tagItem.TagItemRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final TagItemRepository tagItemRepository;;

    public void addBrandTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName);

        if (tag == null) {
            tagRepository.save(new Tag(tagName, TagType.BRAND));
        }
    }

    public void addTag(Item item) {
        TagItem tagItem = tagItemRepository.findByItem(item.getId());
        if (tagItem == null) {
            TagItem newTagItem = new TagItem(item);
        }
    }
}
