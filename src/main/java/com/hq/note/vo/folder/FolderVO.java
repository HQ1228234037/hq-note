package com.hq.note.vo.folder;

import com.hq.note.entity.FolderEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹返回数据实体
 *
 * @author HQ
 **/
@Data
@NoArgsConstructor
@ApiModel("文件夹列表")
public class FolderVO implements Serializable {

    private static final long serialVersionUID = 7483638981102068871L;

    @ApiModelProperty("文件夹id")
    private Long folderId;

    @ApiModelProperty("文件夹名称")
    private String folderName;

    @ApiModelProperty("笔记数量")
    private Integer noteCount;

    @ApiModelProperty("子文件夹列表")
    private List<FolderVO> folderList;

    public FolderVO(FolderEntity folderEntity) {
        this.folderId = folderEntity.getFolderId();
        this.folderName = folderEntity.getFolderName();
        this.noteCount = folderEntity.getNoteCount();
        this.folderList = new ArrayList<>();
    }

}
