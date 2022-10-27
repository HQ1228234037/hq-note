package com.hq.note.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录返回信息实体
 *
 * @author HQ
 **/
@Data
@ApiModel("登录成功返回数据")
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 5623021275383350656L;

    /**
     * 用户id
     **/
    @ApiModelProperty("用户id")
    private Long userId;

    /**
     * 昵称
     **/
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 手机号
     **/
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * token
     **/
    @ApiModelProperty("token")
    private String token;

}
