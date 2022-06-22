package com.minboard.enums;


import lombok.Getter;

@Getter
public enum StatusEnums implements EnumMapperType{

    FLAG_Y("Y"),
    FLAG_N("N");

    private String value;

    StatusEnums(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

}

