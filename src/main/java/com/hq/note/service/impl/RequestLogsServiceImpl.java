package com.hq.note.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hq.note.entity.RequestLogsEntity;
import com.hq.note.mapper.RequestLogsMapper;
import com.hq.note.service.RequestLogsService;
import org.springframework.stereotype.Service;

/**
 * 请求日志(RequestLogs)表服务实现类
 *
 * @author HQ
 * @version v1.0
 * @since 2022-05-29 11:27:26
 */
@Service
public class RequestLogsServiceImpl extends ServiceImpl<RequestLogsMapper, RequestLogsEntity>
        implements RequestLogsService {
}
