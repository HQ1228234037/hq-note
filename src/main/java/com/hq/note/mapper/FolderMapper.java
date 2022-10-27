package com.hq.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.note.entity.FolderEntity;
import org.springframework.stereotype.Repository;

/**
 * 文件夹数据库操作类
 *
 * @author HQ
 **/
@Repository
public interface FolderMapper extends BaseMapper<FolderEntity> {
}
