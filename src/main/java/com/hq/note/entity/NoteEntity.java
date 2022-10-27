package com.hq.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 笔记实体类
 *
 * @author HQ
 **/
@Data
@TableName("hq_note")
public class NoteEntity implements Serializable {

    private static final long serialVersionUID = -8289762448744478000L;

    @TableId(type = IdType.AUTO)
    private Long noteId;

    /**
     * 用户id
     **/
    private Long userId;

    /**
     * 文件夹id
     **/
    private Long folderId;

    /**
     * 笔记标题
     **/
    private String noteTitle;

    /**
     * 笔记内容
     **/
    private String noteContent;

    /**
     * 逻辑删除
     **/
    private Boolean deleted;

    /**
     * 创建时间
     **/
    private Date createDate;

    /**
     * 最后更新时间
     **/
    private Date updateDate;

}
