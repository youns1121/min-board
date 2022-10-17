package com.minboard.enums;

import lombok.Getter;

@Getter
public enum MemberMangeEnums {
    ;

    public enum MemberRoleEnum implements EnumMapperType{
        ADMIN("관리자"),
        USER("사용자");

        private String title;

        MemberRoleEnum(String title) {

            this.title = title;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getValue() {

            return title;
        }
    }

    public enum MemberAccessStatus implements EnumMapperType{

        MEMBER_EXIST("이미 가입한 회원입니다."),
        MEMBER_NOT_FOUND("회원을 찾을 수 없습니다."),
        MEMBER_SIGININ_FAIL("아이디와 패스워드를 확인해 주세요")
        ;

        private String title;

        MemberAccessStatus(String title) {
            this.title = title;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getValue() {
            return title;
        }


    }



}
