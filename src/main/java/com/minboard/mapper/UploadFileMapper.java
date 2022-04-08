package com.minboard.mapper;

import com.minboard.dto.UploadFileDto;
import com.minboard.vo.UploadFileUpdateVo;
import com.minboard.vo.UploadFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadFileMapper {

    /** 파일업로드 리스트 정보 입력하기
     * @param uploadFileList**/
    void insertFileInfoList(List<UploadFileVo> uploadFileList);



    /** 파일업로드 리스트 정보수정하기 **/
    void updateFileInfoList(List<UploadFileUpdateVo> uploadFileList);

    /** 단일 파일정보 찾기 **/
    UploadFileDto findByUploadFile(int id);

    /** 업로드한 파일리스트 가져오기 **/
    List<UploadFileDto> getUploadFileList(int id);

    /** 업데이트할 파일리스트 가져오기기 **/
    List<UploadFileUpdateVo> getUploadFileUpdateList(int id);

    /** 단일파일 삭제하기 **/
    void deleteFile(int id);

    /** 파일 전체 삭제하기 **/
    void deleteAlldFile(int id);

}
