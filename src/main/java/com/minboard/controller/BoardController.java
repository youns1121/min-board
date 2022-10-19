package com.minboard.controller;

import com.minboard.dto.*;
import com.minboard.dto.request.BoardRequestDto;
import com.minboard.service.BoardAdminService;
import com.minboard.service.CommentsService;
import com.minboard.service.BoardFileService;
import com.minboard.service.MemberService;
import com.minboard.service.impl.BoardServiceImpl;
import com.minboard.vo.*;
import com.minboard.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardServiceImpl boardService;
    private final BoardFileService boardFileService;
    private final CommentsService commentsService;
    private final BoardAdminService boardAdminService;
    private final MemberService memberService;

    @GetMapping("/new")
    public String boardSave(Model model, @RequestParam(value = "categoryNumber", required = false) Integer categoryNumber) {

        model.addAttribute("categoryNumber", categoryNumber);
        model.addAttribute("categoryList",  boardAdminService.getBoardCategoryList());
        model.addAttribute("board", BoardSaveDto.builder().build());
         model.addAttribute("boardAdmin",boardAdminService.getBoardCategory(categoryNumber));

        return "html/boardNew";
    }

    @ResponseBody
    @PostMapping("/new")
    public String boardSave(@Validated @ModelAttribute("board") BoardSaveDto boardSaveDto) throws IOException {

        boardService.saveBoard(boardSaveDto);
        boardService.saveBoardFile(boardSaveDto.getFileList(), boardSaveDto.getId());
        return boardSaveDto.getId().toString();
    }

    @ResponseBody
    @PostMapping("/update")
    public String boardModify(@Validated @ModelAttribute("boardUpdateVo") BoardUpdateDto boardUpdateDto) throws IOException {

        boardService.modifyBoard(boardUpdateDto);
        boardService.saveBoardFile(boardUpdateDto.getFileList(), boardUpdateDto.getId());
        return boardUpdateDto.getId().toString();
    }

    @GetMapping("/update/{id}")
    public String boardModify(@PathVariable("id") int id, Model model) {

        model.addAttribute("boardUpdate", boardService.getDetailViewBoard(id));
        model.addAttribute("boardFileList", boardFileService.getBoardFileList(id));
        return "html/boardEdit";
    }

    @ResponseBody
    @PostMapping("/reply")
    public String boardReplySave(@Validated @ModelAttribute("board") BoardSaveDto boardSaveDto) throws IOException {

        boardService.saveBoardReply(boardSaveDto);
        boardService.saveBoardFile(boardSaveDto.getFileList(), boardSaveDto.getId());
        return boardSaveDto.getId().toString();
    }

    @GetMapping("/reply/{id}")
    public String boardReply(@PathVariable("id") int id, Model model){

        model.addAttribute("board", boardService.getBoardReply(id));
        return "html/boardReply";
    }

    @GetMapping("/category/{categoryNumber}")
    public String boardCategoryList(@PathVariable("categoryNumber") int categoryNumber, BoardRequestDto requestDto, Model model) {

        requestDto.setCategoryNumber(categoryNumber);

        model.addAttribute("boardCategory", boardAdminService.getBoardCategory(categoryNumber));
        model.addAttribute("categoryList", boardAdminService.getBoardCategoryList());
        model.addAttribute("boardList", boardService.getBoardList(requestDto));
        return "html/boardList";
    }

    @GetMapping(value = {"/category"})
    public String boardCategoryList(BoardRequestDto requestDto, Model model) {

        model.addAttribute("boardList", boardService.getBoardList(requestDto));
        model.addAttribute("categoryList", boardAdminService.getBoardCategoryList());
        return "html/main";
    }


    @ResponseBody
    @GetMapping("/validation/comments/yn")
    public String validationCommentsYn(@RequestParam int categoryNumber){

        BoardAdminVo boardAdminVo  = boardAdminService.getBoardCategory(categoryNumber);
        return boardAdminVo.getCommentsYn();
    }

    @GetMapping("/view/{id}")
    public String boardDetails(@PathVariable("id") int id, Model model) {

        model.addAttribute("detailViewBoard", boardService.getDetailViewBoard(id));
        return "html/boardDetail";
    }

    @GetMapping("/attach/{fileid}")
    public ResponseEntity<String> boardFileDownload(@PathVariable("fileid") int fileId) throws MalformedURLException {

        DownloadFileDto downloadFileInfo = boardFileService.downloadBoardFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, downloadFileInfo.getContentDisposition())
                .body(downloadFileInfo.getResource());
    }

    @PostMapping("/delete")
    public String boardRemove(int id) {

        BoardVo detailViewBoard = boardService.getDetailViewBoard(id);
        boardService.removeBoard(id);
        return "redirect:/board/category/"+detailViewBoard.getCategoryNumber();
    }

    @ResponseBody
    @PostMapping("/deleteFile")
    public void boardFileRemove(int id){

        boardFileService.deleteFile(id);
    }

    @ResponseBody
    @PostMapping("/comment")
    public void CommentsAdd(CommentsSaveDto commentsSaveDto){

        commentsService.saveComments(commentsSaveDto);
    }


    @GetMapping("/commentsList/{id}")
    public String boardCommentsList(@PathVariable("id") int id, Model model){

        model.addAttribute("commentsList", commentsService.getBoardHierarchicalCommentsList(id));
        return "html/boardCommentsDetail";
    }

    @ResponseBody
    @PostMapping("/comment/update")
    public void boardCommentsModify(BoardCommentsUpdateDto boardCommentsUpdateDto){

        commentsService.modifyComments(boardCommentsUpdateDto);
    }

    @ResponseBody
    @PostMapping("/commentDelete")
    public void boardCommentsRemove(BoardCommentsUpdateDto boardCommentsUpdateDto){

        commentsService.removeComments(boardCommentsUpdateDto);
    }

    @ResponseBody
    @PostMapping("/comment/reply")
    public void commentsReplyAdd(BoardCommentsReplySaveDto commentsReplySaveDto){

        commentsService.saveCommentsReply(commentsReplySaveDto);
    }
}