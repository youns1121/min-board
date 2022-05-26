package com.minboard.enums;

import lombok.Getter;
import lombok.Setter;

public enum MemberRole implements EnumMapperType{
    Admin("관리자"),
    USER("사용자");

    private String title;

    MemberRole(String title) {

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
