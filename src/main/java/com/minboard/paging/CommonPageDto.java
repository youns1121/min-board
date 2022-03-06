package com.minboard.paging;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommonPageDto extends PaginationDto{

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;

    /** 등록일 */
    private LocalDateTime createTime;

    /** 수정일 */
    private LocalDateTime updateTime;

    public CommonPageDto(PaginationInfo paginationInfo, LocalDateTime createTime, LocalDateTime updateTime) {
        this.paginationInfo = paginationInfo;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
    }
}
