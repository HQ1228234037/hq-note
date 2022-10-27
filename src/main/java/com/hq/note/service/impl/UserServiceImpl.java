package com.hq.note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hq.note.dto.user.LoginDTO;
import com.hq.note.dto.user.UpdatePasswordDTO;
import com.hq.note.entity.UserEntity;
import com.hq.note.mapper.UserMapper;
import com.hq.note.service.UserService;
import com.hq.note.vo.ResultVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户 service 实现类
 *
 * @author HQ
 **/
@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Resource
    private UserMapper userMapper;

    @Override
    public UserEntity login(LoginDTO loginDTO) {
        // 获取用户信息
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginDTO.getPhone());
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity != null) {
            // 校验密码
            if (passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    @Override
    public ResultVO<String> updatePassword(UpdatePasswordDTO dto) {
        // 获取用户信息
        UserEntity userEntity = userMapper.selectById(dto.getUserId());
        if (userEntity == null) {
            return ResultVO.error("用户不存在");
        }

        // 校验旧密码
        boolean verify = passwordEncoder.matches(dto.getOldPassword(), userEntity.getPassword());
        if (verify) {
            // 修改密码
            userEntity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            int updateCount = userMapper.updateById(userEntity);
            if (updateCount > 0) {
                return new ResultVO<>();
            } else {
                return ResultVO.error("更新失败");
            }
        } else {
            return ResultVO.error("旧密码错误");
        }
    }

}
