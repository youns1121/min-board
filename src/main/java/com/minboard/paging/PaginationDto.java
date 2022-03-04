package com.minboard.paging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PaginationDto {

    /** 페이지 번호 **/
    private Integer pageNo;

    /** 페이지당 게시물 갯수 **/
    private Integer recordsPerPage;

    /** 화면 하단 페이지 사이즈 **/
    private Integer pageSize;

    public PaginationDto() {
        this.pageNo = 1;
        this.recordsPerPage = 10;
        this.pageSize = 10;
    }

    public int getStartPage() {
        return (pageNo - 1) * recordsPerPage;
    }

}
