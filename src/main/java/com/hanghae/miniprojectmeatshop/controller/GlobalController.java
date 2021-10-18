package com.hanghae.miniprojectmeatshop.controller;

import com.hanghae.miniprojectmeatshop.defaultResponse.DefaultResponse;
import com.hanghae.miniprojectmeatshop.defaultResponse.ResponseMessage;
import com.hanghae.miniprojectmeatshop.defaultResponse.StatusCode;
import com.hanghae.miniprojectmeatshop.defaultResponse.SuccessYn;
import com.hanghae.miniprojectmeatshop.exception.ItemNotFoundException;
import jdk.net.SocketFlow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalController {

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<String> ItemNotFoundException(ItemNotFoundException exception) {
        return new ResponseEntity(DefaultResponse.res(SuccessYn.NO, StatusCode.BAD_REQUEST, ResponseMessage.ITEM_NOT_FOUND, null), HttpStatus.OK);
    }
}
