package com.hq.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hq.note.dto.note.AddNoteDTO;
import com.hq.note.dto.note.UpdateNoteDTO;
import com.hq.note.entity.NoteEntity;
import com.hq.note.mapper.NoteMapper;
import com.hq.note.service.FolderService;
import com.hq.note.service.NoteService;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.note.NoteListVO;
import com.hq.note.vo.note.NoteVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 笔记 service 实现类
 *
 * @author HQ
 **/
@Service
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteMapper noteMapper;

    @Resource
    private FolderService folderService;

    @Override
    public ResultVO<List<NoteListVO>> getNoteList(Long folderId, Long userId) {
        ResultVO<List<NoteListVO>> resultVO = new ResultVO<>();
        List<NoteListVO> noteVOList = new ArrayList<>();
        resultVO.setData(noteVOList);

        // 获取笔记列表
        QueryWrapper<NoteEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("folder_id", folderId);
        queryWrapper.select("note_id", "note_title");
        queryWrapper.orderByAsc("create_date");
        List<NoteEntity> noteEntities = noteMapper.selectList(queryWrapper);
        noteEntities.forEach(noteEntity -> noteVOList.add(new NoteListVO(noteEntity)));

        return resultVO;
    }

    @Override
    public ResultVO<NoteVO> getNote(Long noteId, Long userId) {
        ResultVO<NoteVO> resultVO = new ResultVO<>();

        // 获取笔记
        QueryWrapper<NoteEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("note_id", noteId);
        queryWrapper.eq("user_id", userId);
        NoteEntity noteEntity = noteMapper.selectOne(queryWrapper);
        if (noteEntity == null) {
            return ResultVO.error("笔记不存在");
        }

        resultVO.setData(new NoteVO(noteEntity));
        return resultVO;
    }

    @Override
    @Transactional
    public ResultVO<String> addNote(AddNoteDTO dto) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setUserId(dto.getUserId());
        noteEntity.setFolderId(dto.getFolderId());
        noteEntity.setNoteTitle(dto.getNoteTitle());
        noteEntity.setNoteContent(dto.getNoteContent());
        int insertCount = noteMapper.insert(noteEntity);
        if (insertCount > 0) {
            // 修改文件夹笔记数
            folderService.updateNoteCount(dto.getFolderId(), true);

            return new ResultVO<>();
        }
        return ResultVO.error("添加失败");
    }

    @Override
    public ResultVO<String> deleteNote(Long noteId, Long userId) {
        // 获取笔记信息
        NoteEntity noteEntity = noteMapper.selectById(noteId);
        if (noteEntity == null) {
            return ResultVO.error("笔记不存在");
        }

        // 删除笔记
        QueryWrapper<NoteEntity> deleteQueryWrapper = new QueryWrapper<>();
        deleteQueryWrapper.eq("user_id", userId);
        deleteQueryWrapper.eq("note_id", noteId);
        int deleteCount = noteMapper.delete(deleteQueryWrapper);
        if (deleteCount > 0) {
            // 修改文件夹笔记数
            folderService.updateNoteCount(noteEntity.getFolderId(), false);

            return new ResultVO<>();
        }

        return ResultVO.error("删除失败");
    }

    @Override
    public ResultVO<String> updateNote(UpdateNoteDTO dto) {
        QueryWrapper<NoteEntity> updateQueryWrapper = new QueryWrapper<>();
        updateQueryWrapper.eq("note_id", dto.getNoteId());
        updateQueryWrapper.eq("user_id", dto.getUserId());

        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setNoteTitle(dto.getNoteTitle());
        noteEntity.setNoteContent(dto.getNoteContent());

        int updateCount = noteMapper.update(noteEntity, updateQueryWrapper);
        if (updateCount > 0) {
            return new ResultVO<>();
        }

        return ResultVO.error("更新失败");
    }

}
