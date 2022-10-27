package com.hq.note.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录请求参数实体
 *
 * @author HQ
 **/
@Data
@ApiModel()
public class LoginDTO implements Serializable {

    private static final long serialVersionUID = -5736064461673173755L;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "密码（md5加密后再传）", required = true)
    @NotBlank(message = "密码不能为空")
    private String password;

}
