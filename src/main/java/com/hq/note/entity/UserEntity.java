package com.hq.note.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author HQ
 **/
@Data
@TableName("hq_user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 89872580559843921L;

    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 昵称
     **/
    private String nickname;

    /**
     * 手机号
     **/
    private String phone;

    /**
     * 密码
     **/
    private String password;

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
