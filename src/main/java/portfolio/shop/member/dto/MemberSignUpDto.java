package portfolio.shop.member.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberSignUpDto {

    @NotBlank
    @Length(min = 5, max = 11)
    @Pattern(regexp = "^[a-z0-9_]{5,11}$")
    private String loginId;

    @NotBlank
    @Length(min = 1, max = 10)
    private String username;

    @NotBlank
    @Length(min = 8, max = 30)
    private String password;

    @NotBlank
    @Length(min = 8, max = 30)
    private String passwordConfirm;

    @NotBlank
    @Email
    private String email;

}
