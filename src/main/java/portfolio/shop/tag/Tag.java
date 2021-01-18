package portfolio.shop.tag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import portfolio.shop.tagItem.TagItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    private String tagName;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    public Tag(String tagName, TagType tagType) {
        this.tagName = tagName;
        this.tagType = tagType;
    }

    @OneToMany(mappedBy = "tag")
    public List<TagItem> tagItems = new ArrayList<>();

    public Tag(TagItem... tagItems) {

    }

}
