package com.hq.note.dto.file;

import com.hq.note.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 上传图片请求参数
 *
 * @author HQ
 **/
@Data
@ApiModel()
@EqualsAndHashCode(callSuper = true)
public class UploadImageDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 6622842949768029672L;

    @NotNull(message = "图片不能为空")
    @ApiModelProperty(value = "上传图片", required = true)
    private MultipartFile file;

}
