package com.hq.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求日志(RequestLogs)表实体类
 *
 * @author HQ
 */
@Data
@TableName("hq_request_logs")
public class RequestLogsEntity implements Serializable {

    private static final long serialVersionUID = -75728172778223582L;

    /**
     * 日志id
     */
    @TableId(type = IdType.AUTO)
    private Long logsId;

    /**
     * 请求用户id
     */
    private Long userId;

    /**
     * 请求uri
     */
    private String uri;

    /**
     * 请求数据
     */
    private String requestData;

    /**
     * token
     **/
    private String token;

    /**
     * 客户端ip
     */
    private String ip;

    /**
     * 逻辑删除
     */
    private Object deleted;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后修改时间
     */
    private Date updateDate;

}
