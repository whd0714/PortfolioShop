package portfolio.shop.tag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolio.shop.Item.Item;
import portfolio.shop.Item.ItemRepository;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;
import portfolio.shop.tagItem.TagItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepository;
    private final TagService tagService;
    private final ItemRepository itemRepository;


    @GetMapping("/myPage/keyword")
    public String keywordFormView(@CurrentUser Member member, Model model, TagType tagType) {
        if (member != null) {
            model.addAttribute(member);
        }

        List<Tag> tagList = tagRepository.findByTagType(TagType.BRAND);
        model.addAttribute("tagList",tagList);

        return "member/keyword";
    }

 /*   @PostMapping("/myPage/brandTag")
    @ResponseBody
    public Result inputBrandTag(@RequestBody TagForm tagForm) {

        String tagName = tagForm.getTagName();
        tagService.addBrandTag(tagName);

        List<Tag> tagList = tagRepository.findAll();

        JSONArray jsonArray = new JSONArray();
        JSONArray tagListToJsonArray = jsonArray.fromObject(tagList);

        return new Result(tagListToJsonArray);
    }*/

    @PostMapping("/myPage/brandTag")
    @ResponseBody
    public JSONArray inputBrandTag(@RequestBody TagForm tagForm) {

        String tagName = tagForm.getTagName();
        System.out.println("tagName="+tagName);

        tagService.addBrandTag(tagName);

        List<Tag> tagList = tagRepository.findAll();

        JSONArray jsonArray = new JSONArray();
        JSONArray tagListToJsonArray = jsonArray.fromObject(tagList);

        return tagListToJsonArray;
    /*    ObjectMapper mapper = new ObjectMapper();

        String json = "바이";
        String json2 = "하이";
        try {
            Map<String, String> map = new HashMap<>();
            map.put("result1", json);
            map.put("result2", json2);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;*/
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T result;
    }

    @GetMapping("/goods/{itemId}/setting/tags")
    public String addGoodsTagForm(@CurrentUser Member member, Model model, @PathVariable("itemId") Long itemId) {
        if (member != null) {
            model.addAttribute(member);
        }

        Optional<Item> findItem = itemRepository.findById(itemId);

        findItem.ifPresent(item->{
            model.addAttribute(item);
            tagService.addTag(item);

        });

        return "item/tagForm";

    }

    @PostMapping("/myPage/tagDelte")
    @ResponseBody
    public void deleteTag(@RequestBody String tagName) {
        System.out.println("tagName = " + tagName);
    }

}
