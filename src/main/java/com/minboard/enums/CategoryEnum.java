package com.minboard.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CategoryEnum {
    NOTICE("공지게시판",0),
    FREE("자유게시판", 1),
    FILE("파일게시판", 2),
    COMMENTS("댓글게시판", 3)
    ;

    private final String categoryName;
    private final int categoryCode;

    CategoryEnum(String categoryName, int categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }
}
