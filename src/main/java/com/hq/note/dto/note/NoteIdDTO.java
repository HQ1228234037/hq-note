package com.hq.note.dto.note;

import com.hq.note.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 笔记id请求参数实体
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class NoteIdDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -5667398671020788398L;

    @NotNull(message = "笔记id不能为空")
    @Min(value = 1, message = "笔记id必须大于0")
    @ApiModelProperty(value = "笔记id", required = true)
    private Long noteId;

}
