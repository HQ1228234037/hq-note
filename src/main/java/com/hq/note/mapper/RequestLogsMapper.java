package com.hq.note.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hq.note.entity.RequestLogsEntity;
import org.springframework.stereotype.Repository;

/**
 * 请求日志(RequestLogs)表数据库访问层
 *
 * @author HQ
 * @version v1.0
 * @since 2022-05-29 11:27:26
 */
@Repository
public interface RequestLogsMapper extends BaseMapper<RequestLogsEntity> {
}
