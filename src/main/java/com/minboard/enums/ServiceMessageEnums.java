package com.minboard.enums;

public enum ServiceMessageEnums implements EnumMapperType {

    NO_DATA("정보를 찾을 수 없습니다.")
    ;

    private String value;

    ServiceMessageEnums(String value) {
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
