package com.hq.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hq.note.dto.folder.AddFolderDTO;
import com.hq.note.dto.folder.UpdateFolderDTO;
import com.hq.note.entity.FolderEntity;
import com.hq.note.exception.FolderException;
import com.hq.note.mapper.FolderMapper;
import com.hq.note.service.FolderService;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.folder.FolderVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹 service 实现类
 *
 * @author HQ
 **/
@Service
public class FolderServiceImpl implements FolderService {

    @Resource
    private FolderMapper folderMapper;

    @Override
    public ResultVO<FolderVO> getFolderList(Long userId) {
        ResultVO<FolderVO> resultVO = new ResultVO<>();

        // 获取用户文件夹列表
        QueryWrapper<FolderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByAsc("create_date");
        List<FolderEntity> folderEntities = folderMapper.selectList(queryWrapper);

        // 根目录
        FolderVO root = new FolderVO();
        root.setFolderId(0L);
        root.setFolderName("");
        root.setFolderList(new ArrayList<>());
        resultVO.setData(root);

        if (CollectionUtils.isEmpty(folderEntities)) {
            return resultVO;
        }

        // 填充目录
        addSubfolder(root, folderEntities);

        return resultVO;
    }

    @Override
    public ResultVO<String> addFolder(AddFolderDTO dto) {
        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setFolderName(dto.getFolderName());
        folderEntity.setUserId(dto.getUserId());
        folderEntity.setNoteCount(0);
        folderEntity.setParentId(dto.getParentId());
        int insertCount = folderMapper.insert(folderEntity);
        if (insertCount > 0) {
            return new ResultVO<>();
        }
        return ResultVO.error("添加失败");
    }

    @Override
    public ResultVO<String> deleteFolder(Long folderId, Long userId) {
        // 获取文件夹信息
        FolderEntity folderEntity = folderMapper.selectById(folderId);
        if (folderEntity.getNoteCount() > 0) {
            return ResultVO.error("文件夹下存在笔记，无法删除");
        }

        // 查询是否存在子文件夹
        QueryWrapper<FolderEntity> folderQueryWrapper = new QueryWrapper<>();
        folderQueryWrapper.eq("parent_id", folderId);
        Long subfolderCount = folderMapper.selectCount(folderQueryWrapper);
        if (subfolderCount > 0) {
            return ResultVO.error("文件夹下存在子文件夹，无法删除");
        }

        // 删除文件夹
        QueryWrapper<FolderEntity> deleteQueryWrapper = new QueryWrapper<>();
        deleteQueryWrapper.eq("user_id", userId);
        deleteQueryWrapper.eq("folder_id", folderId);
        int deleteCount = folderMapper.delete(deleteQueryWrapper);
        if (deleteCount > 0) {
            return new ResultVO<>();
        }

        return ResultVO.error("删除失败");
    }

    @Override
    public ResultVO<String> updateFolder(UpdateFolderDTO dto) {
        QueryWrapper<FolderEntity> updateQueryWrapper = new QueryWrapper<>();
        updateQueryWrapper.eq("folder_id", dto.getFolderId());
        updateQueryWrapper.eq("user_id", dto.getUserId());

        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setFolderName(dto.getFolderName());

        int updateCount = folderMapper.update(folderEntity, updateQueryWrapper);
        if (updateCount > 0) {
            return new ResultVO<>();
        }

        return ResultVO.error("更新失败");
    }

    @Override
    public void updateNoteCount(Long folderId, boolean addCount) {
        // 获取文件夹信息
        FolderEntity folderEntity = folderMapper.selectById(folderId);
        if (folderEntity == null) {
            throw new FolderException("文件夹不存在");
        }

        if (addCount) {
            folderEntity.setNoteCount(folderEntity.getNoteCount() + 1);
        } else {
            folderEntity.setNoteCount(folderEntity.getNoteCount() - 1);
        }

        folderMapper.updateById(folderEntity);
    }

    /**
     * 添加子文件夹
     *
     * @param folderVO       父文件夹
     * @param folderEntities 文件夹集合
     * @author HQ
     **/
    private void addSubfolder(FolderVO folderVO, List<FolderEntity> folderEntities) {
        for (FolderEntity folderEntity : folderEntities) {
            if (folderVO.getFolderId().equals(folderEntity.getParentId())) {
                FolderVO subfolder = new FolderVO(folderEntity);
                folderVO.getFolderList().add(subfolder);
                addSubfolder(subfolder, folderEntities);
            }
        }
    }

}
