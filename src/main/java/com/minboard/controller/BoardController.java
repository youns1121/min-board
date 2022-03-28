package com.minboard.controller;


import com.minboard.dto.BoardDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.service.BoardService;
import com.minboard.service.FileStoreService;
import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardUpdateVo;
import com.minboard.vo.BoardVo;
import com.minboard.vo.UploadFileVo;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;


import javax.servlet.http.HttpServletRequest;
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
    public String formBoard(Model model, BoardVo boardVo) {
        model.addAttribute("board", boardVo.builder().build());
        return "html/boardNew";
    }

    /** 게시물 생성하기 **/
    @PostMapping("/new")
    public String createBoard(@Validated @ModelAttribute("board") BoardSaveVo boardSaveVo, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) throws IOException {

        boardService.createBoard(boardSaveVo);
        List<UploadFileVo> uploadFileList = fileStoreService.storeFiles(boardSaveVo.getFileList(), boardSaveVo.getId());
        if(!CollectionUtils.isEmpty(uploadFileList)) {
            uploadFileMapper.insertFileList(uploadFileList);
        }
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "html/boardNew";
        }
        redirectAttributes.addAttribute("id", boardSaveVo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/view/{id}";
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
    @PostMapping("/update")
    public String updateBoard(@Validated @ModelAttribute("boardUpdateVo") BoardUpdateVo boardUpdateVo,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "html/boardEdit";
        }

        boardService.updateBoard(boardUpdateVo);
        redirectAttributes.addAttribute("id", boardUpdateVo.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/view/{id}";
    }

    /** 게시물 수정페이지 **/
    @GetMapping("/update/{id}")
    public String getDetailViewUpdateBoard(@PathVariable("id") int id, Model model) {

        BoardUpdateVo boardUpdateVo = boardService.getDetailViewUpdateBoard(id);
        List<UploadFileDto> uploadFileList = fileStoreService.getUploadFileList(id);
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
}


