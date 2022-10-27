package com.hq.note.controller;

import com.hq.note.dto.BaseDTO;
import com.hq.note.dto.folder.AddFolderDTO;
import com.hq.note.dto.folder.FolderIdDTO;
import com.hq.note.dto.folder.UpdateFolderDTO;
import com.hq.note.service.FolderService;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.folder.FolderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 文件夹 controller 类
 *
 * @author HQ
 **/
@RestController
@Api(tags = "文件夹接口")
@RequestMapping("/folder")
public class FolderController {

    @Resource
    private FolderService folderService;

    /**
     * 获取文件夹列表
     *
     * @param dto 用户id
     * @return 文件夹列表
     * @author HQ
     **/
    @ApiOperation("获取文件夹列表")
    @PostMapping("/getFolderList")
    public ResultVO<FolderVO> getFolderList(@RequestBody @Valid BaseDTO dto) {
        return folderService.getFolderList(dto.getUserId());
    }

    /**
     * 添加文件夹
     *
     * @param dto 文件夹信息
     * @return 添加结果
     * @author HQ
     **/
    @ApiOperation("添加文件夹")
    @PostMapping("/addFolder")
    public ResultVO<String> addFolder(@RequestBody @Valid AddFolderDTO dto) {
        return folderService.addFolder(dto);
    }

    /**
     * 删除文件夹
     *
     * @param dto 文件夹id
     * @return 删除结果
     * @author HQ
     **/
    @ApiOperation("删除文件夹")
    @DeleteMapping("/deleteFolder")
    public ResultVO<String> deleteFolder(@RequestBody @Valid FolderIdDTO dto) {
        return folderService.deleteFolder(dto.getFolderId(), dto.getUserId());
    }

    /**
     * 修改文件夹
     *
     * @param dto 文件夹信息
     * @return 修改结果
     * @author HQ
     **/
    @ApiOperation("修改文件夹")
    @PutMapping("/updateFolder")
    public ResultVO<String> updateFolder(@RequestBody @Valid UpdateFolderDTO dto) {
        return folderService.updateFolder(dto);
    }

}
