package portfolio.shop.member;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import portfolio.shop.member.dto.MemberSignUpDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@RequiredArgsConstructor
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void signUpMethod() throws Exception {

        mockMvc.perform(post("/sign-up")
                .param("email","member1@naver.com")
                .param("loginId","member1")
                .param("password","123456789")
                .param("passwordConfirm","123456789")
                .param("username","userA")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andDo(print());
    }

    @Test
    public void signUpMethodEmailError() throws Exception {

        mockMvc.perform(post("/sign-up")
                .param("email","member")
                .param("loginId","member1")
                .param("password","123456789")
                .param("passwordConfirm","123456789")
                .param("username","userA")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("member/sign-up"))
                .andDo(print());
    }

    @Test
    public void signUpMethodLoginIdError() throws Exception {

        mockMvc.perform(post("/sign-up")
                .param("email","member@naver.com")
                .param("loginId","member+&")
                .param("password","123456789")
                .param("passwordConfirm","123456789")
                .param("username","userA")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("member/sign-up"))
                .andDo(print());
    }

    @Test
    public void signUpMethodPasswordError() throws Exception {

        mockMvc.perform(post("/sign-up")
                .param("email","member@naver.com")
                .param("loginId","member1")
                .param("password","123456")
                .param("passwordConfirm","123456789")
                .param("username","userA")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("member/sign-up"))
                .andDo(print());
    }


}