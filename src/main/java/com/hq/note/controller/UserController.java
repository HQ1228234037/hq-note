package com.hq.note.controller;

import com.hq.note.constant.RedisConstant;
import com.hq.note.dto.user.LoginDTO;
import com.hq.note.dto.user.UpdatePasswordDTO;
import com.hq.note.entity.UserEntity;
import com.hq.note.service.UserService;
import com.hq.note.vo.ResultVO;
import com.hq.note.vo.user.LoginVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户 controller 类
 *
 * @author HQ
 **/
@RestController
@Api(tags = "用户登录相关")
@RequestMapping("/user")
public class UserController {

    @Value("${token.key}")
    private String key;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserService userService;

    /**
     * 登录接口
     *
     * @param dto 登录请求参数
     * @return 登录结果
     * @author HQ
     **/
    @PostMapping("/login")
    @ApiOperation("登录")
    public ResultVO<LoginVO> login(@RequestBody @Valid LoginDTO dto) {
        UserEntity userEntity = userService.login(dto);
        if (userEntity == null) {
            return ResultVO.error("用户名或密码错误");
        }

        // 创建token
        Map<String, Object> params = new HashMap<>(3);
        params.put("userId", userEntity.getUserId());
        params.put("phone", userEntity.getPhone());
        params.put("nickname", userEntity.getNickname());
        String token = Jwts.builder()
                .setSubject(userEntity.getPhone())
                .addClaims(params)
                .setExpiration(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        // 缓存token
        stringRedisTemplate.opsForValue().set(RedisConstant.LOGIN_TOKEN + userEntity.getUserId(), token, Duration.ofDays(1));

        // 返回登录成功信息
        ResultVO<LoginVO> resultVO = new ResultVO<>();
        LoginVO loginVO = new LoginVO();
        loginVO.setUserId(userEntity.getUserId());
        loginVO.setPhone(userEntity.getPhone());
        loginVO.setNickName(userEntity.getNickname());
        loginVO.setToken(token);
        resultVO.setData(loginVO);

        return resultVO;
    }

    /**
     * 修改密码
     *
     * @param dto 新旧密码
     * @return 修改结果
     * @author HQ
     **/
    @ApiOperation("修改密码")
    @PutMapping("/updatePassword")
    public ResultVO<String> updatePassword(@RequestBody @Valid UpdatePasswordDTO dto) {
        return userService.updatePassword(dto);
    }

}
