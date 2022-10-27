package com.hq.note.service;

import com.hq.note.dto.user.LoginDTO;
import com.hq.note.dto.user.UpdatePasswordDTO;
import com.hq.note.entity.UserEntity;
import com.hq.note.vo.ResultVO;

/**
 * 用户 service 接口
 *
 * @author HQ
 **/
public interface UserService {

    /**
     * 登录
     *
     * @param loginDTO 登录信息
     * @return 登录用户信息
     * @author HQ
     **/
    UserEntity login(LoginDTO loginDTO);

    /**
     * 修改密码
     *
     * @param dto 新旧密码信息
     * @return 修改结果
     * @author HQ
     **/
    ResultVO<String> updatePassword(UpdatePasswordDTO dto);

}
