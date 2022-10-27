package com.hq.note.vo;

import com.hq.note.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回数据 VO 类
 *
 * @author HQ
 **/
@Data
@ApiModel
public class ResultVO<T> {

    @ApiModelProperty(value = "状态码", required = true)
    private Integer code;

    @ApiModelProperty(value = "返回提示消息", required = true)
    private String message;

    @ApiModelProperty(value = "返回数据", required = true)
    private T data;

    public ResultVO() {
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getValue();
    }

    public ResultVO(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getValue();
    }

    /**
     * 异常返回
     *
     * @param message 异常提示
     * @return 返回数据
     * @author HQ
     **/
    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> resultVO = new ResultVO<>(ResultCode.ERROR);
        resultVO.setMessage(message);
        return resultVO;
    }

}
