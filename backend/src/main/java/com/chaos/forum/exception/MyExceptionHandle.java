package com.chaos.forum.exception;

import com.chaos.forum.vo.ResultVO;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * { 自定义异常处理类 }
 * </p>
 *
 * @Author kay
 * 2019-09-20 20:31
 */
@ControllerAdvice
public class MyExceptionHandle extends RuntimeException {

    @ExceptionHandler(value = DataException.class)
    @ResponseBody
    protected ResultVO handle(DataException e) {
        DataException dataException = e;
        return new ResultVO(dataException.getResultEnum());
    }

}
