package com.minboard.enums;

import lombok.Getter;

@Getter
public enum MemberMangeEnums {
    ;

    public enum MemberRoleEnum implements EnumMapperType{
        Admin("관리자"),
        USER("사용자");

        private String title;

        MemberRoleEnum(String title) {

            this.title = title;
        }

        @Override
        public String getCode() {

            return name();
        }

        @Override
        public String getTitle() {

            return title;
        }
    }


}
