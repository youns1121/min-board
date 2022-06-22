package com.minboard.paging;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommonPageDto extends PaginationDto {

    private PaginationInfo paginationInfo;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
