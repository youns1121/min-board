package com.minboard.service;

import com.minboard.dto.DownloadFileDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.vo.UploadFileUpdateVo;
import com.minboard.vo.UploadFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FileStoreService {

    /** 파일 전체경로 가져오기 **/
    String getFullPath(String filePath);

    /** 파일리스트 저장하기 **/
    List<UploadFileVo> storeFiles(List<MultipartFile> multipartFiles, int boardId) throws IOException;

    /** 파일업로드 리스트 정보 입력하기 **/
    void insertFileInfoList(List<UploadFileVo> uploadFileList);

    /** 파일업로드 리스트 정보 수정하기 **/
    void updateFileInfoList(List<UploadFileUpdateVo> uploadFileList);

    /** 단일파일 저장하기
     * @return**/
    UploadFileVo storeFile(MultipartFile multipartFile, int boardId) throws IOException;

    /** 파일리스트 수정하기 **/
    List<UploadFileUpdateVo> storeFilesUpdate(List<MultipartFile> multipartFiles, int boardId) throws IOException;

    /** 단일파일 수정하기 **/
    UploadFileUpdateVo storeFileUpdate(MultipartFile multipartFile, int boardId) throws IOException;

    /** 파일이름 생성하기 **/
    String createStoreFileName();

    /** 파일확장자 추출하기 **/
    String extractExt(String originalFileName);

    /** 업로드한 파일리스트 가져오기 **/
    List<UploadFileDto> getUploadFileList(int id);

    /** 업데이트할 파일리스트 가져오기 **/
    List<UploadFileUpdateVo> getUploadFileUpdateList(int id);

    /** 파일이름 확장자 허용 체크 **/
    boolean fileExtensionInboundCheck(MultipartFile multipartFile) throws IOException;

    /** 파일정보 찾기 **/
    UploadFileDto findByUploadFile(int id);

    /** 단일파일 삭제하기 **/
    void deleteFile(int id);

    /** 파일다운로드**/
    DownloadFileDto downloadAttachedFile(int fileId) throws MalformedURLException;
}
