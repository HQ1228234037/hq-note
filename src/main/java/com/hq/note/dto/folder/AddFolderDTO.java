package com.hq.note.dto.folder;

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
 * 添加文件夹请求参数实体
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class AddFolderDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 9187138027791831480L;

    @NotBlank(message = "文件夹名称不能为空")
    @ApiModelProperty(value = "文件夹名称", required = true)
    private String folderName;

    @NotNull(message = "父文件夹id不能为空")
    @Min(value = 0, message = "父文件夹id不能小于0")
    @ApiModelProperty(value = "父文件夹id", required = true)
    private Long parentId;

}
