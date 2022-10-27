package com.hq.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.note.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户数据库操作类
 *
 * @author HQ
 **/
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
}
