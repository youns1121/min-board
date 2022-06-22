package com.minboard.service;

import com.minboard.dto.BoardSaveDto;
import com.minboard.dto.request.BoardRequestDto;
import com.minboard.vo.BoardVo;
import com.minboard.dto.BoardUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface BoardService {
    List<BoardVo> getBoardList(BoardRequestDto boardRequestDto);

    BoardVo getDetailViewBoard(int id);

    BoardVo getBoardReply(int id);

    void saveBoard(BoardSaveDto boardSaveDto) throws IOException;

    void saveBoardFile(List<MultipartFile> fileList, int id) throws IOException;

    void removeBoard(int id);

    void modifyBoard(BoardUpdateDto boardUpdateVo);

    void saveBoardReply(BoardSaveDto boardSaveDto);


}
