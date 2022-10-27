package com.hq.note.config;

import com.hq.note.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 参数校验异常处理器
 *
 * @author HQ
 **/
@Slf4j
@ControllerAdvice
public class ValidParamExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO<String> allExceptionHandler(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            // 获取异常信息
            BindingResult exceptions = methodArgumentNotValidException.getBindingResult();
            // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
            if (exceptions.hasErrors()) {
                List<ObjectError> errors = exceptions.getAllErrors();
                if (!errors.isEmpty()) {
                    // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                    FieldError fieldError = (FieldError) errors.get(0);
                    return ResultVO.error(fieldError.getDefaultMessage());
                }
            }
            return ResultVO.error("请求参数异常");
        } else {
            log.error(e.getMessage(), e);
            return ResultVO.error(e.getMessage());
        }
    }

}
