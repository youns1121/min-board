package com.minboard.paging;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonPageDto extends PaginationDto {

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

    /** 등록일 **/
    private LocalDateTime createTime;

    /** 수정일 **/
    private LocalDateTime updateTime;

}
