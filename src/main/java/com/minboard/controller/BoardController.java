package com.minboard.controller;

import com.minboard.dto.BoardDto;
import com.minboard.dto.CommentsDto;
import com.minboard.dto.DownloadFileDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.service.BoardService;
import com.minboard.service.CommentService;
import com.minboard.service.FileStoreService;
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

    private final BoardService boardService;
    private final FileStoreService fileStoreService;
    private final CommentService commentService;

    @GetMapping("/new")
    public String boardSave(Model model, BoardSaveVo boardSaveVo) {

        model.addAttribute("board", boardSaveVo.builder().build());
        return "html/boardNew";
    }

    @ResponseBody
    @PostMapping("/new")
    public String boardSave(@Validated @ModelAttribute("board") BoardSaveVo boardSaveVo) throws IOException {

        boardService.createBoard(boardSaveVo);
        boardService.saveBoardFile(boardSaveVo);
        return boardSaveVo.getId().toString();
    }

    @GetMapping("/list")
    public String boardList(@ModelAttribute("boardDto") BoardDto boardDto, Model model) {

        List<BoardDto> boardList = boardService.getBoardList(boardDto);
        model.addAttribute("boardList", boardList);
        return "html/boardList";
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

        BoardDto boardUpdateVo = boardService.getDetailViewBoard(id);
        List<UploadFileDto> uploadFileList = fileStoreService.getUploadFileList(id);
        model.addAttribute("uploadFileUpdateVo", UploadFileUpdateVo.builder().build());
        model.addAttribute("boardUpdateVo", boardUpdateVo);
        model.addAttribute("uploadFileList", uploadFileList);
        return "html/boardEdit";
    }

    @PostMapping("/delete")
    public String boardRemove(int id) {

        boardService.deleteBoard(id);
        return "redirect:/board/list";
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