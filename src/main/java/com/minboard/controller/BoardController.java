package com.minboard.controller;

import com.minboard.dto.*;
import com.minboard.service.CommentService;
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
    private final CommentService commentService;

    @GetMapping("/new")
    public String boardSave(Model model, BoardSaveVo boardSaveVo, BoardDto boardDto,
                            @RequestParam("categorynumber") String categoryNumber) {

        List<BoardDto> categoryList = boardService.selectBoardCategoryList(boardDto);
        model.addAttribute("categoryNumber", categoryNumber);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("board",boardSaveVo);
        return "html/boardNew";
    }

    @ResponseBody
    @PostMapping("/new")
    public String boardSave(@Validated @ModelAttribute("board") BoardSaveVo boardSaveVo) throws IOException {

        BoardDto boardDto = boardService.selectBoardCategory(boardSaveVo.getBoardAdminId());

        boolean validationFileCheck = fileStoreService.validationFileCheck(boardDto, boardSaveVo);

        if(validationFileCheck == false){
            return "false";
        }

        boardService.saveBoard(boardSaveVo);
        boardService.saveBoardFile(boardSaveVo);
        return boardSaveVo.getId().toString();
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

        BoardDto detailViewBoard = boardService.selectBoardReply(boardSaveVo.getId());
        model.addAttribute("board", detailViewBoard);
        return "html/boardReply";
    }

    @GetMapping("/category/{categoryNumber}")
    public String boardCategoryList(@PathVariable("categoryNumber") int categoryNumber, BoardDto boardDto, Model model) {

        boardDto.setCategoryNumber(categoryNumber);
        List<BoardDto> categoryList = boardService.selectBoardCategoryList(boardDto);
        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
    }

    @GetMapping("/category")
    public String boardCategoryList(BoardDto boardDto, Model model) {

        List<BoardDto> categoryList = boardService.selectBoardCategoryList(boardDto);
        List<BoardDto> boardList = boardService.selectBoardAllList(boardDto);
        model.addAttribute("boardList", boardList);
        model.addAttribute("categoryList", categoryList);
        return "html/boardList";
    }

    @ResponseBody
    @GetMapping("/validation/file/yn")
    public String validationFileYn(@RequestParam int id){
        BoardDto boardDto = boardService.selectBoardCategory(id);
        return boardDto.getAttachedFileYn();
    }

    @ResponseBody
    @GetMapping("/validation/reply/yn")
    public String validationReplyYn(@RequestParam int id){
        BoardDto boardDto = boardService.getDetailViewBoard(id);
        return boardDto.getCommentsYn();
    }

    @ResponseBody
    @GetMapping("/validation/file/count")
    public String validationFileCount(@RequestParam int id){
        BoardDto boardDto = boardService.selectBoardCategory(id);
        return boardDto.getAttachedFileCount().toString();
    }

    @ResponseBody
    @GetMapping("/validation/comments/yn")
    public String validationCommentsYn(@RequestParam int categoryNumber){
        BoardDto boardDto = boardService.selectBoardCategoryNumber(categoryNumber);
        return boardDto.getCommentsYn();
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

    @ResponseBody
    @PostMapping("/update")
    public String boardModify(@Validated @ModelAttribute("boardUpdateVo") BoardUpdateVo boardUpdateVo,
                              @ModelAttribute("uploadFileUpdateVo") UploadFileUpdateVo uploadFileUpdateVo
                              ) throws IOException {

        boardService.updateBoardFile(boardUpdateVo);
        boardService.updateBoard(boardUpdateVo);
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

    @PostMapping("/delete")
    public String boardRemove(int id) {

        boardService.deleteBoard(id);
        return "redirect:/admin";
    }

    @PostMapping("/deleteFile")
    public void boardFileRemove(int id){
        fileStoreService.deleteFile(id);
    }

    @ResponseBody
    @PostMapping("/comment")
    public void CommentsAdd(CommentsSaveVo commentsSaveVo){
        commentService.insertComments(commentsSaveVo);
    }


    @GetMapping("/commentsList/{id}")
    public String boardCommentsList(@PathVariable("id") int id, Model model){

        List<CommentsDto> commentsList = commentService.getBoardHierarchicalCommentsList(id);
        model.addAttribute("commentsList", commentsList);
        return "html/boardCommentsDetail";
    }

    @ResponseBody
    @PostMapping("/comment/update")
    public void boardCommentsModify(CommentsUpdateVo commentsUpdateVo){
        commentService.updateComments(commentsUpdateVo);
    }

    @ResponseBody
    @PostMapping("/commentDelete")
    public void boardCommentsRemove(CommentsDto commentsDto){
        commentService.deleteComment(commentsDto);
    }

    @ResponseBody
    @PostMapping("/comment/reply")
    public void commentsReplyAdd(CommentsReplySaveVo commentsReplySaveVo){
        commentService.insertCommentsReply(commentsReplySaveVo);
    }
}