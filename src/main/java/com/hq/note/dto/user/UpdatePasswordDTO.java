package com.hq.note.dto.user;

import com.hq.note.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 更新密码请求参数实体
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class UpdatePasswordDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -4989995889816105982L;

    @NotBlank(message = "旧密码不能为空")
    @ApiModelProperty(value = "旧密码（md5加密后再传）", required = true)
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @ApiModelProperty(value = "新密码（md5加密后再传）", required = true)
    private String newPassword;

}
