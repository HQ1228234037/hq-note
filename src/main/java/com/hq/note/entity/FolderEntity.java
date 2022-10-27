package com.hq.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件夹实体类
 *
 * @author HQ
 **/
@Data
@TableName("hq_folder")
public class FolderEntity implements Serializable {

    private static final long serialVersionUID = 6900271036035461751L;

    @TableId(type = IdType.AUTO)
    private Long folderId;

    /**
     * 文件夹名称
     **/
    private String folderName;

    /**
     * 用户id
     **/
    private Long userId;

    /**
     * 文件夹下笔记数量
     **/
    private Integer noteCount;

    /**
     * 父文件夹id
     **/
    private Long parentId;

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
