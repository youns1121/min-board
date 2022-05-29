package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {

    private Long seq;

    @NotBlank(message = "아이디는 필수값 입니다.")
    private String userName;

    @NotBlank(message = "패스워드는 필수값 입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @NotBlank(message = "이름은 필수값 입니다.")
    private String name;

    private String birthday;

    @NotBlank(message = "성별은 필수값 입니다.")
    private String gender;

    private String memberRole;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public MemberDto(String userName, String password, String name,
                     String birthday, String gender, String memberRole,
                     LocalDateTime createTime, LocalDateTime updateTime, String delYn) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delYn = delYn;
    }

    public MemberDto() {
    }
}
