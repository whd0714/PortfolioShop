package portfolio.shop.tag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id @GeneratedValue
    private Long id;

    private String tagName;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    public Tag(String tagName, TagType tagType) {
        this.tagName = tagName;
        this.tagType = tagType;
    }
}
