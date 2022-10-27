package com.hq.note.controller;

import com.hq.note.dto.file.UploadImageDTO;
import com.hq.note.utils.AliOssUtils;
import com.hq.note.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;

/**
 * 文件 controller 类
 *
 * @author HQ
 */
@Slf4j
@RestController
@Api(tags = "文件上传")
@RequestMapping("/file")
public class FileController {

    @Resource
    private AliOssUtils aliOssUtils;

    /**
     * 上传图片
     *
     * @param dto: 上传图片
     **/
    @PostMapping("/uploadImage")
    @ApiOperation("上传图片")
    public ResultVO<String> uploadImage(@Valid UploadImageDTO dto) {
        try {
            String imageUrl = aliOssUtils.uploadFile(dto.getFile());

            ResultVO<String> resultVO = new ResultVO<>();
            resultVO.setData(imageUrl);
            return resultVO;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ResultVO.error("上传失败");
        }
    }

}
