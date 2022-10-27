package com.hq.note.controller;

import com.hq.note.dto.folder.FolderIdDTO;
import com.hq.note.dto.note.AddNoteDTO;
import com.hq.note.dto.note.NoteIdDTO;
import com.hq.note.dto.note.UpdateNoteDTO;
import com.hq.note.service.NoteService;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.note.NoteListVO;
import com.hq.note.vo.note.NoteVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 笔记 controller 类
 *
 * @author HQ
 **/
@RestController
@Api(tags = "笔记接口")
@RequestMapping("/note")
public class NoteController {

    @Resource
    private NoteService noteService;

    /**
     * 获取笔记列表
     *
     * @param dto 请求参数
     * @return 笔记列表
     * @author HQ
     **/
    @ApiOperation("获取笔记列表")
    @PostMapping("/getNoteList")
    public ResultVO<List<NoteListVO>> getNoteList(@RequestBody @Valid FolderIdDTO dto) {
        return noteService.getNoteList(dto.getFolderId(), dto.getUserId());
    }

    /**
     * 获取笔记内容
     *
     * @param dto 笔记id
     * @return 笔记列表
     * @author HQ
     **/
    @ApiOperation("获取笔记内容")
    @PostMapping("/getNote")
    public ResultVO<NoteVO> getNote(@RequestBody @Valid NoteIdDTO dto) {
        return noteService.getNote(dto.getNoteId(), dto.getUserId());
    }

    /**
     * 添加笔记
     *
     * @param dto 添加笔记
     * @return 添加结果
     * @author HQ
     **/
    @ApiOperation("添加笔记")
    @PostMapping("/addNote")
    public ResultVO<String> addNote(@RequestBody @Valid AddNoteDTO dto) {
        return noteService.addNote(dto);
    }

    /**
     * 删除笔记
     *
     * @param dto 笔记id
     * @return 删除结果
     * @author HQ
     **/
    @ApiOperation("删除笔记")
    @DeleteMapping("/deleteNote")
    public ResultVO<String> deleteNote(@RequestBody @Valid NoteIdDTO dto) {
        return noteService.deleteNote(dto.getNoteId(), dto.getUserId());
    }

    /**
     * 修改笔记
     *
     * @param dto 笔记信息
     * @return 修改结果
     * @author HQ
     **/
    @ApiOperation("修改笔记")
    @PutMapping("/updateNote")
    public ResultVO<String> updateNote(@RequestBody @Valid UpdateNoteDTO dto) {
        return noteService.updateNote(dto);
    }

}
