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
 * 添加笔记请求参数
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class AddNoteDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 6453096882242407625L;

    @NotNull(message = "文件夹id不能为空")
    @Min(value = 0, message = "文件夹id不能小于0")
    @ApiModelProperty(value = "文件夹id", required = true)
    private Long folderId;

    @NotNull(message = "笔记标题不能为空")
    @ApiModelProperty(value = "笔记标题", required = true)
    private String noteTitle;

    @NotNull(message = "笔记内容不能为空")
    @ApiModelProperty(value = "笔记内容", required = true)
    private String noteContent;

}
