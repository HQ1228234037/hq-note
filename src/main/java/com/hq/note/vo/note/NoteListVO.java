package com.hq.note.vo.note;

import com.hq.note.entity.NoteEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 笔记列表返回参数实体
 *
 * @author HQ
 **/
@Data
@NoArgsConstructor
@ApiModel("笔记列表信息")
public class NoteListVO implements Serializable {

    private static final long serialVersionUID = -6878360576641444223L;

    @ApiModelProperty("笔记id")
    private Long noteId;

    @ApiModelProperty("笔记标题")
    private String noteTitle;

    public NoteListVO(NoteEntity noteEntity) {
        this.noteId = noteEntity.getNoteId();
        this.noteTitle = noteEntity.getNoteTitle();
    }

}
