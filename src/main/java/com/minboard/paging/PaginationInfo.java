package com.minboard.paging;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationInfo {

    /** 페이징 계산에 필요한 파라미터들이 담긴 클래스 */
    private PaginationDto paginationDto;

    /** 전체 데이터 개수 */
    private int totalRecordCount;

    /** 전체 페이지 개수 */
    private int totalPageCount;

    /** 페이지 리스트의 첫 페이지 번호 */
    private int firstPage;

    /** 페이지 리스트의 마지막 페이지 번호 */
    private int lastPage;

    /** SQL의 조건절에 사용되는 첫 RNUM */
    private int firstRecordIndex;

    /** SQL의 조건절에 사용되는 마지막 RNUM */
    private int lastRecordIndex;

    /** 이전 페이지 존재 여부 */
    private boolean hasPreviousPage;

    /** 다음 페이지 존재 여부 */
    private boolean hasNextPage;


    public PaginationInfo(PaginationDto paginationDto) {
        if (paginationDto.getCurrentPageNo() < 1) {
            paginationDto.setCurrentPageNo(1);
        }
        if (paginationDto.getRecordsPerPage() < 1 || paginationDto.getRecordsPerPage() > 100) {
            paginationDto.setRecordsPerPage(10);
        }
        if (paginationDto.getPageSize() < 5 || paginationDto.getPageSize() > 20) {
            paginationDto.setPageSize(10);
        }
        this.paginationDto = paginationDto;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
        if (totalRecordCount > 0) {
            calculation();
        }
    }

    private void calculation() {

        /* 전체 페이지 수 (현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장) */
        totalPageCount = ((totalRecordCount - 1) / paginationDto.getRecordsPerPage()) + 1;
        if (paginationDto.getCurrentPageNo() > totalPageCount) {
            paginationDto.setCurrentPageNo(totalPageCount);
        }

        /* 페이지 리스트의 첫 페이지 번호 */
        firstPage = ((paginationDto.getCurrentPageNo() - 1) / paginationDto.getPageSize()) * paginationDto.getPageSize() + 1;

        /* 페이지 리스트의 마지막 페이지 번호 (마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수를 저장) */
        lastPage = firstPage + paginationDto.getPageSize() - 1;
        if (lastPage > totalPageCount) {
            lastPage = totalPageCount;
        }

        /* SQL의 조건절에 사용되는 첫번째 RNUM */
        firstRecordIndex = (paginationDto.getCurrentPageNo() - 1) * paginationDto.getRecordsPerPage();

        /* SQL의 조건절에 사용되는 마지막 RNUM */
        lastRecordIndex = paginationDto.getCurrentPageNo() * paginationDto.getRecordsPerPage();

        /* 이전 페이지 존재 여부 */
        hasPreviousPage = firstPage != 1;

        /* 다음 페이지 존재 여부 */
        hasNextPage = (lastPage * paginationDto.getRecordsPerPage()) < totalRecordCount;
    }



}
