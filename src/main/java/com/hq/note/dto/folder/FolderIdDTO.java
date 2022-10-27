package com.hq.note.dto.folder;

import com.hq.note.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 文件夹id请求参数实体
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class FolderIdDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5453437374280900338L;

    @NotNull(message = "文件夹id不能为空")
    @Min(value = 0, message = "文件夹id不能小于0")
    @ApiModelProperty(value = "文件夹id", required = true)
    private Long folderId;

}
