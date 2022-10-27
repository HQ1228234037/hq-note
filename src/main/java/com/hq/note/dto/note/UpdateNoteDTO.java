package com.hq.note.dto.note;

import com.hq.note.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 更新笔记请求参数实体
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class UpdateNoteDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = -3861967382875131798L;

    @NotNull(message = "笔记id不能为空")
    @Min(value = 1, message = "笔记id必须大于0")
    @ApiModelProperty(value = "笔记id", required = true)
    private Long noteId;

    @NotBlank(message = "笔记标题不能为空")
    @ApiModelProperty(value = "笔记标题", required = true)
    private String noteTitle;

    @NotNull(message = "笔记内容不能为空")
    @ApiModelProperty(value = "笔记内容", required = true)
    private String noteContent;

}
