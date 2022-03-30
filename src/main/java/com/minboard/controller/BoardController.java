package com.minboard.controller;

import com.minboard.dto.BoardDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.service.BoardService;
import com.minboard.service.FileStoreService;
import com.minboard.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final FileStoreService fileStoreService;
    private final UploadFileMapper uploadFileMapper;

    /** 게시물 생성페이지 **/
    @GetMapping("/new")
    public String formBoard(Model model, BoardSaveVo boardSaveVo) {
        model.addAttribute("board", boardSaveVo.builder().build());
        return "html/boardNew";
    }

    /** 게시물 생성하기 **/
    @ResponseBody
    @PostMapping("/new")
    public String createBoard(@Validated @ModelAttribute("board") BoardSaveVo boardSaveVo, BindingResult bindingResult
    ) throws IOException {

        boardService.createBoard(boardSaveVo);
        if(!CollectionUtils.isEmpty(boardSaveVo.getFileList())) {
            List<UploadFileVo> uploadFileList = fileStoreService.storeFiles(boardSaveVo.getFileList(), boardSaveVo.getId());
            uploadFileMapper.insertFileList(uploadFileList);
        }
//        if(bindingResult.hasErrors()){
//            log.info("errors={}", bindingResult);
//            return "html/boardNew";
//        }
        return boardSaveVo.getId().toString();
    }

    /** 게시물 리스트 **/
    @GetMapping("/list")
    public String getBoardPagingList(@ModelAttribute("boardDto") BoardDto boardDto, Model model) {
        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
    }

    /** 게시물 상세페이지 **/
    @GetMapping("/view/{id}")
    public String getDetailBoardView(@PathVariable("id") int id, Model model) {
        BoardDto detailViewBoard = boardService.getDetailViewBoard(id);
        List<UploadFileDto> uploadFileList = fileStoreService.getUploadFileList(id);
        model.addAttribute("detailViewBoard", detailViewBoard);
        model.addAttribute("uploadFileList", uploadFileList);
        return "html/boardDetail";
    }

    /** 첨부파일 다운로드  **/
    @GetMapping("/attach/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable int fileId) throws MalformedURLException {

        UploadFileDto uploadFile = uploadFileMapper.findByUploadFile(fileId);
        String storeFileName = uploadFile.getStoreFileName();
        String uploadFileName = uploadFile.getOriginalFileName();

        UrlResource resource = new UrlResource("file:" + fileStoreService.getFullPath(storeFileName));
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    /** 게시물 수정하기 **/
    @ResponseBody
    @PostMapping("/update")
    public String updateBoard(@Validated @ModelAttribute("boardUpdateVo") BoardUpdateVo boardUpdateVo,
                              @ModelAttribute("uploadFileUpdateVo") UploadFileUpdateVo uploadFileUpdateVo,
                              BindingResult bindingResult) throws IOException {

        boardService.updateBoard(boardUpdateVo);
        if(!CollectionUtils.isEmpty(boardUpdateVo.getFileList())){
            List<UploadFileUpdateVo> uploadFileUpdate = fileStoreService.storeFilesUpdate(boardUpdateVo.getFileList(),
                    boardUpdateVo.getId());
            uploadFileMapper.updateFileList(uploadFileUpdate);
        }
//        if(bindingResult.hasErrors()){
//            log.info("errors={}", bindingResult);
//            return "html/boardEdit";
//        }
        return boardUpdateVo.getId().toString();
    }

    /** 게시물 수정페이지 **/
    @GetMapping("/update/{id}")
    public String getDetailViewUpdateBoard(@PathVariable("id") int id, Model model) {

        BoardDto boardUpdateVo = boardService.getDetailViewBoard(id);
        List<UploadFileDto> uploadFileList = fileStoreService.getUploadFileList(id);
        model.addAttribute("uploadFileUpdateVo", UploadFileUpdateVo.builder().build());
        model.addAttribute("boardUpdateVo", boardUpdateVo);
        model.addAttribute("uploadFileList", uploadFileList);
        return "html/boardEdit";
    }

    /** 게시물 삭제하기 **/
    @PostMapping("/delete")
    public String deleteBoard(int id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

    /** 단일 파일삭제 **/
    @PostMapping("/deleteFile")
    public void deleteFile(int id){
        fileStoreService.deleteFile(id);
    }
}


