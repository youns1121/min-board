package com.minboard.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class PaginationDto {

    /** 페이지 번호 **/
    private Integer currentPageNo ;

    /** 페이지당 게시물 갯수 **/
    private Integer recordsPerPage;

    /** 화면 하단 페이지 사이즈 **/
    private Integer pageSize;

    public PaginationDto() {
        this.currentPageNo  = 1;
        this.recordsPerPage = 10;
        this.pageSize = 10;
    }

    public String makeQueryString(int pageNo) {
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("currentPageNo", pageNo)
                .queryParam("recordsPerPage", recordsPerPage)
                .queryParam("pageSize", pageSize)
                .build()
                .encode();
        return uriComponents.toUriString();
    }

}
