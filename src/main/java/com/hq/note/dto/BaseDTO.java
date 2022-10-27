package com.hq.note.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请求公共参数
 *
 * @author HQ
 **/
@Data
@ApiModel
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 7662732087913377826L;

    /**
     * 用户id
     **/
    @ApiModelProperty(value = "用户id", required = true)
    @NotNull(message = "用户id不能为空")
    @Min(value = 1, message = "用户id不能小于1")
    private Long userId;

}
