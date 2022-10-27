package com.hq.note.service;

import com.hq.note.dto.folder.AddFolderDTO;
import com.hq.note.dto.folder.UpdateFolderDTO;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.folder.FolderVO;

/**
 * 文件夹 service 接口
 *
 * @author HQ
 **/
public interface FolderService {

    /**
     * 获取用户文件夹列表
     *
     * @param userId 用户id
     * @return 文件夹列表
     * @author HQ
     **/
    ResultVO<FolderVO> getFolderList(Long userId);

    /**
     * 添加文件夹
     *
     * @param dto 文件夹信息
     * @return 添加结果
     * @author HQ
     **/
    ResultVO<String> addFolder(AddFolderDTO dto);

    /**
     * 删除文件夹
     *
     * @param folderId 文件夹id
     * @param userId   用户id
     * @return 删除结果
     * @author HQ
     **/
    ResultVO<String> deleteFolder(Long folderId, Long userId);

    /**
     * 更新文件夹
     *
     * @param dto 文件夹信息
     * @return 更新结果
     * @author HQ
     **/
    ResultVO<String> updateFolder(UpdateFolderDTO dto);

    /**
     * 修改文件夹下笔记数
     *
     * @param folderId 文件夹id
     * @param addCount true：加一；false：减一
     * @author HQ
     **/
    void updateNoteCount(Long folderId, boolean addCount);

}
