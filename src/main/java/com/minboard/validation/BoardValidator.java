package com.minboard.validation;

import com.minboard.vo.BoardSaveVo;
import com.minboard.vo.BoardVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return BoardVo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        BoardSaveVo boardSaveVo = (BoardSaveVo) target;

        if(boardSaveVo.getContents() == null || "".equals(boardSaveVo.getContents()) ||
                boardSaveVo.getContents().trim().length() < 20 || boardSaveVo.getContents().trim().length() > 100){
            errors.reject("contents", "내용은 20자 이상 100자 이하");
        }
    }
}
