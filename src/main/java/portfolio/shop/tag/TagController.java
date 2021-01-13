package portfolio.shop.tag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONArray;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Text;
import portfolio.shop.member.CurrentUser;
import portfolio.shop.member.Member;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TagController {

    private final TagRepository tagRepository;
    private final TagService tagService;

    @GetMapping("/myPage/keyword")
    public String keywordFormView(@CurrentUser Member member, Model model, TagType tagType) {
        if (member != null) {
            model.addAttribute(member);
        }

        List<Tag> tagList = tagRepository.findByTagType(TagType.BRAND);
        model.addAttribute("tagList",tagList);

        return "member/keyword";
    }

    @PostMapping("/myPage/brandTag")
    @ResponseBody
    public Result inputBrandTag(@RequestBody TagForm tagForm) {

        String tagName = tagForm.getTagName();
        tagService.addBrandTag(tagName);

        List<Tag> tagList = tagRepository.findAll();

        JSONArray jsonArray = new JSONArray();
        JSONArray tagListToJsonArray = jsonArray.fromObject(tagList);

        return new Result(tagListToJsonArray);
    }

    /*@PostMapping("/myPage/brandTag")
    @ResponseBody
    public Result inputBrandTag(@RequestBody TagForm tagForm) {

        String tagName = tagForm.getTagName();

        tagService.addBrandTag(tagName);

        ObjectMapper mapper = new ObjectMapper();

        String json = "{\"name\":mkyong\"}";
        String json2 = "{\"name\" : \"" +tagName+ "\"}";
        try {
            Map<String, String> map = mapper.readValue(json2, Map.class);
            return new Result(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }*/

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T result;
    }

}
