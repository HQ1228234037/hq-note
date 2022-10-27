package com.hq.note.service;

import com.hq.note.dto.note.AddNoteDTO;
import com.hq.note.dto.note.UpdateNoteDTO;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.note.NoteListVO;
import com.hq.note.vo.note.NoteVO;

import java.util.List;

/**
 * 笔记 service 接口
 *
 * @author HQ
 **/
public interface NoteService {

    /**
     * 获取笔记列表
     *
     * @param folderId 文件夹id
     * @param userId   用户id
     * @return 笔记列表
     * @author HQ
     **/
    ResultVO<List<NoteListVO>> getNoteList(Long folderId, Long userId);

    /**
     * 获取笔记内容
     *
     * @param noteId 笔记id
     * @param userId 用户id
     * @return 笔记信息
     * @author HQ
     **/
    ResultVO<NoteVO> getNote(Long noteId, Long userId);

    /**
     * 添加笔记
     *
     * @param dto 笔记信息
     * @return 添加结果
     * @author HQ
     **/
    ResultVO<String> addNote(AddNoteDTO dto);

    /**
     * 删除笔记
     *
     * @param noteId 笔记id
     * @param userId 用户id
     * @return 删除结果
     * @author HQ
     **/
    ResultVO<String> deleteNote(Long noteId, Long userId);

    /**
     * 修改笔记内容
     *
     * @param dto 笔记信息
     * @return 更新结果
     * @author HQ
     **/
    ResultVO<String> updateNote(UpdateNoteDTO dto);

}
