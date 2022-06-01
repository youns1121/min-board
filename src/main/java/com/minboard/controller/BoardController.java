package com.minboard.controller;

import com.minboard.dto.*;
import com.minboard.service.BoardAdminService;
import com.minboard.service.CommentsService;
import com.minboard.service.FileStoreService;
import com.minboard.service.impl.BoardServiceImpl;
import com.minboard.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardServiceImpl boardService;
    private final FileStoreService fileStoreService;
    private final CommentsService commentsService;
    private final BoardAdminService boardAdminService;

    @GetMapping
    public String boardHeader(Model model){

        List<BoardAdminDto> categoryList = boardAdminService.getBoardCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "html/header/boardHeader";
    }

    @GetMapping("/new")
    public String boardSave(Model model, BoardSaveVo boardSaveVo,
                            @RequestParam("categoryNumber") int categoryNumber) {

        List<BoardAdminDto> categoryList = boardAdminService.getBoardCategoryList();
        BoardAdminDto boardAdminDto = boardAdminService.getBoardCategory(categoryNumber);
        model.addAttribute("categoryNumber", categoryNumber);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("board", boardSaveVo);
        model.addAttribute("boardAdmin",boardAdminDto);
        return "html/boardNew";
    }

    @ResponseBody
    @PostMapping("/new")
    public String boardSave(@Validated @ModelAttribute("board")  BoardSaveVo boardSaveVo) throws IOException {

        boardService.saveBoard(boardSaveVo);
        boardService.saveBoardFile(boardSaveVo);
        return boardSaveVo.getId().toString();
    }

    @ResponseBody
    @PostMapping("/update")
    public String boardModify(@Validated @ModelAttribute("boardUpdateVo") BoardUpdateVo boardUpdateVo,
                              @ModelAttribute("uploadFileUpdateVo") UploadFileUpdateVo uploadFileUpdateVo
    ) throws IOException {

        boardService.updateBoardFile(boardUpdateVo);
        boardService.modifyBoard(boardUpdateVo);
        return boardUpdateVo.getId().toString();
    }

    @GetMapping("/update/{id}")
    public String boardModify(@PathVariable("id") int id, Model model) {

        BoardDto boardDto = boardService.getDetailViewBoard(id);
        List<UploadFileDto> uploadFileList = fileStoreService.getUploadFileList(id);
        model.addAttribute("boardUpdateVo", boardDto);
        model.addAttribute("uploadFileList", uploadFileList);
        return "html/boardEdit";
    }

    @ResponseBody
    @PostMapping("/reply")
    public String boardReplySave(@Validated @ModelAttribute("board") BoardSaveVo boardSaveVo) throws IOException {

        boardService.saveBoardReply(boardSaveVo);
        boardService.saveBoardFile(boardSaveVo);
        return boardSaveVo.getId().toString();
    }

    @GetMapping("/reply/{id}")
    public String boardReply(BoardSaveVo boardSaveVo, Model model){

        BoardDto detailViewBoard = boardService.getBoardReply(boardSaveVo.getId());
        model.addAttribute("board", detailViewBoard);
        return "html/boardReply";
    }

    @GetMapping("/category/{categoryNumber}")
    public String boardCategoryList(@PathVariable("categoryNumber") int categoryNumber,BoardDto boardDto, Model model) {

        List<BoardAdminDto> categoryList = boardAdminService.getBoardCategoryList();
        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        boardDto.setCategoryNumber(categoryNumber);
        BoardAdminDto boardCategory = boardAdminService.getBoardCategory(categoryNumber);

        model.addAttribute("boardCategory", boardCategory);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
    }

    @GetMapping("/category")
    public String boardCategoryList(BoardDto boardDto, Model model) {

        List<BoardAdminDto> categoryList = boardAdminService.getBoardCategoryList();
        List<BoardDto> boardList = boardService.getBoardList(boardDto);

        model.addAttribute("boardList", boardList);
        model.addAttribute("categoryList", categoryList);
        return "html/main";
    }


    @ResponseBody
    @GetMapping("/validation/comments/yn")
    public String validationCommentsYn(@RequestParam int categoryNumber){
        BoardAdminDto boardCategory  = boardAdminService.getBoardCategory(categoryNumber);
        return boardCategory.getCommentsYn();
    }

    @GetMapping("/view/{id}")
    public String boardDetails(@PathVariable("id") int id, Model model) {
        BoardDto detailViewBoard = boardService.getDetailViewBoard(id);
        List<UploadFileDto> uploadFileList = fileStoreService.getUploadFileList(id);
        model.addAttribute("detailViewBoard", detailViewBoard);
        model.addAttribute("uploadFileList", uploadFileList);
        return "html/boardDetail";
    }

    /** 첨부파일 다운로드 **/
    @GetMapping("/attach/{fileId}")
    public ResponseEntity<String> boardFileDownload(@PathVariable int fileId) throws MalformedURLException {

        DownloadFileDto downloadFileInfo = fileStoreService.downloadAttachedFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, downloadFileInfo.getContentDisposition())
                .body(downloadFileInfo.getResource());
    }

    @PostMapping("/delete")
    public String boardRemove(int id) {

        BoardDto detailViewBoard = boardService.getDetailViewBoard(id);
        boardService.removeBoard(id);
        return "redirect:/board/category/"+detailViewBoard.getCategoryNumber();
    }

    @ResponseBody
    @PostMapping("/deleteFile")
    public void boardFileRemove(int id){
        fileStoreService.deleteFile(id);
    }

    @ResponseBody
    @PostMapping("/comment")
    public void CommentsAdd(CommentsSaveVo commentsSaveVo){
        commentsService.insertComments(commentsSaveVo);
    }


    @GetMapping("/commentsList/{id}")
    public String boardCommentsList(@PathVariable("id") int id, Model model){

        List<CommentsDto> commentsList = commentsService.getBoardHierarchicalCommentsList(id);
        model.addAttribute("commentsList", commentsList);
        return "html/boardCommentsDetail";
    }

    @ResponseBody
    @PostMapping("/comment/update")
    public void boardCommentsModify(CommentsUpdateVo commentsUpdateVo){
        commentsService.updateComments(commentsUpdateVo);
    }

    @ResponseBody
    @PostMapping("/commentDelete")
    public void boardCommentsRemove(CommentsDto commentsDto){
        commentsService.deleteComments(commentsDto);
    }

    @ResponseBody
    @PostMapping("/comment/reply")
    public void commentsReplyAdd(CommentsReplySaveVo commentsReplySaveVo){
        commentsService.insertCommentsReply(commentsReplySaveVo);
    }
}