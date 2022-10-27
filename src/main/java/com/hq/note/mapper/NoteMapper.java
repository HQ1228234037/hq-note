package com.hq.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.note.entity.NoteEntity;
import org.springframework.stereotype.Repository;

/**
 * 笔记数据库操作类
 *
 * @author HQ
 **/
@Repository
public interface NoteMapper extends BaseMapper<NoteEntity> {
}
